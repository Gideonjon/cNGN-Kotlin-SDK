package com.example.cngn

import com.example.cngn.api.CngnService
import com.example.cngn.exceptions.CngnApiException
import com.example.cngn.exceptions.CngnNetworkException
import com.example.cngn.models.ApiResponse
import com.example.cngn.models.BalanceItem
import com.example.cngn.models.Bank
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

/**
 * High-level Kotlin client for the cNGN HTTP API.
 *
 * This is a small, opinionated wrapper around Retrofit. It:
 *  - Adds the Authorization and Content-Type headers automatically
 *  - Exposes suspend functions with proper exceptions
 */
class CngnClient internal constructor(
    private val service: CngnService
) {

    companion object {
        private const val DEFAULT_BASE_URL = "https://api.cngn.co/"

        /**
         * Creates a new instance of [CngnClient].
         *
         * @param apiKey Your cNGN API key. It will be sent as `Authorization: Bearer <apiKey>`.
         * @param baseUrl Base URL of the API, defaults to the official production endpoint.
         * @param enableLogging Whether to enable HTTP logging via OkHttp's HttpLoggingInterceptor.
         */
        fun create(
            apiKey: String,
            baseUrl: String = DEFAULT_BASE_URL,
            enableLogging: Boolean = false
        ): CngnClient {
            val authInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }

            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)

            if (enableLogging) {
                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                clientBuilder.addInterceptor(logging)
            }

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            val service = retrofit.create(CngnService::class.java)
            return CngnClient(service)
        }
    }

    /**
     * Returns the current wallet balance.
     *
     * NOTE: The official docs show both encrypted and decrypted examples.
     * This SDK maps the decrypted structure. If you have encryption enabled
     * on your account, you'll need to decrypt the payload before using it.
     */
    suspend fun getBalance(): ApiResponse<List<BalanceItem>> =
        safeCall { service.getBalance() }

    /**
     * Returns the list of Nigerian banks that can be used with cNGN.
     */
    suspend fun getBankList(): ApiResponse<List<Bank>> =
        safeCall { service.getBankList() }

    private suspend fun <T> safeCall(block: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return try {
            val response = withContext(Dispatchers.IO) { block() }
            if (response.status != 200) {
                throw CngnApiException(response.status, response.message)
            }
            response
        } catch (ioe: IOException) {
            throw CngnNetworkException("Network error while calling cNGN API", ioe)
        }
    }
}
