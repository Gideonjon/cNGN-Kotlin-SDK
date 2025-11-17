package com.example.cngn

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * This is a very small usage example. It won't pass unless you set a valid API key
 * and (optionally) run against a sandbox environment, if available.
 */
class CngnClientUsageExampleTest {

    @Test
    fun exampleUsage() {
        // Replace with your actual API key before running.
        val apiKey = System.getenv("CNGN_API_KEY") ?: return

        val client = CngnClient.create(
            apiKey = apiKey,
            enableLogging = true
        )

        runBlocking {
            val balance = client.getBalance()
            println("Balance response: $balance")
            assertNotNull(balance.data)
        }
    }
}
