package com.example.cngn.models

/**
 * Generic wrapper used by cNGN endpoints.
 *
 * Example (decrypted) response:
 *
 * {
 *   "status": 200,
 *   "message": "Balance fetched successfully",
 *   "data": [ ... ]
 * }
 */
data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T?
)

/**
 * Decrypted representation of a single balance entry.
 *
 * Docs example:
 * {
 *   "asset_type": "credit_alphanum4",
 *   "asset_code": "CNGN",
 *   "balance": "0"
 * }
 */
data class BalanceItem(
    val asset_type: String,
    val asset_code: String,
    val balance: String
)

/**
 * Decrypted representation of a bank entry from the Get Bank List endpoint.
 *
 * Docs example:
 * {
 *   "name": "Access Bank Nigeria",
 *   "slug": "access",
 *   "code": "044",
 *   "country": "NG",
 *   "nibss_bank_code": "000014"
 * }
 */
data class Bank(
    val name: String,
    val slug: String,
    val code: String,
    val country: String,
    val nibss_bank_code: String
)
