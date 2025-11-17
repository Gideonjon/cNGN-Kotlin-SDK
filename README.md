# cNGN Kotlin SDK (Starter)

A minimal, opinionated Kotlin SDK for the [cNGN API](https://docs.cngn.co/integrations/endpoints).

This starter uses:

- Kotlin JVM
- Coroutines (`suspend` functions)
- Retrofit + OkHttp
- Moshi for JSON

Currently implemented endpoints:

- `GET /v1/api/balance`
- `GET /v1/api/banks`

You can extend this to support the remaining endpoints shown in the docs.

## Installation (local)

```bash
./gradlew build
```

## Usage

```kotlin
import com.example.cngn.CngnClient
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val apiKey = "YOUR_API_KEY"

    val client = CngnClient.create(
        apiKey = apiKey,
        // baseUrl = "https://api.cngn.co/", // optional
        enableLogging = true
    )

    val balanceResponse = client.getBalance()
    println("Status: ${'$'}{balanceResponse.status}")
    println("Message: ${'$'}{balanceResponse.message}")
    println("Balances: ${'$'}{balanceResponse.data}")

    val banksResponse = client.getBankList()
    println("Banks: ${'$'}{banksResponse.data}")
}
```

## Extending the SDK

To add more endpoints:

1. Update `CngnService` with the new Retrofit method, e.g.

```kotlin
@POST("v1/api/withdraw")
suspend fun withdraw(@Body body: WithdrawRequest): ApiResponse<WithdrawResponse>
```

2. Create request/response models in `models/`.
3. Add a wrapper function in `CngnClient` that calls the service method via `safeCall`.
4. Write tests and update the README.

Remember that the official docs sometimes show the `data` field as an encrypted string and also provide an example of the decrypted structure. This SDK maps the **decrypted** structure directly. If you have encryption enabled in your environment, you may need to plug in decryption logic before deserializing, or adjust the `ApiResponse` type to use `String` for `data` and handle decryption externally.

---

This is just a starter template — feel free to rename packages (`com.example.cngn` → `com.yourorg.cngn`) and publish it to Maven Central or JitPack.
