package com.example.cngn.exceptions

/**
 * Base exception for errors coming from the cNGN SDK.
 */
open class CngnException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)

/**
 * Represents a non-successful API response with a status code and message
 * returned by the cNGN backend.
 */
class CngnApiException(
    val status: Int,
    val apiMessage: String
) : CngnException("cNGN API error (status=$status): $apiMessage")

/**
 * Represents networking or serialization issues while talking to the API.
 */
class CngnNetworkException(
    message: String,
    cause: Throwable
) : CngnException(message, cause)
