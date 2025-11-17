package com.example.cngn.api

import com.example.cngn.models.ApiResponse
import com.example.cngn.models.BalanceItem
import com.example.cngn.models.Bank
import retrofit2.http.GET

/**
 * Retrofit definition of the core cNGN HTTP API.
 *
 * Docs: https://docs.cngn.co/integrations/endpoints
 */
interface CngnService {

    /**
     * GET https://api.cngn.co/v1/api/balance
     *
     * This endpoint enables merchants to retrieve their wallet balance.
     */
    @GET("v1/api/balance")
    suspend fun getBalance(): ApiResponse<List<BalanceItem>>

    /**
     * GET https://api.cngn.co/v1/api/banks
     *
     * This endpoint returns the list of Nigerian banks.
     */
    @GET("v1/api/banks")
    suspend fun getBankList(): ApiResponse<List<Bank>>
}
