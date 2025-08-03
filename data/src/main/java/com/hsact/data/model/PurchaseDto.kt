package com.hsact.data.model

import kotlinx.serialization.Serializable

/**
 * DTO representing a single purchase record.
 *
 * @property date ISO-8601 formatted date string, e.g., `"2025-08-01T00:00:00Z"`.
 * @property name List of purchased item names on that date.
 */
@Serializable
data class PurchaseDto(
    val date: String,
    val name: List<String>
)