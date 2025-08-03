package com.hsact.domain.model

import java.time.Instant

/**
 * Represents a list of purchase item names made on a specific date.
 *
 * @property date The date of the purchase in UTC.
 * @property names The list of item names purchased on that date.
 */
data class Purchase(
    val date: Instant,
    val names: List<String>
)