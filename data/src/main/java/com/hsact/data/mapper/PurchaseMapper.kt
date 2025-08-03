package com.hsact.data.mapper

import com.hsact.data.model.PurchaseDto
import com.hsact.domain.model.Purchase
import java.time.Instant

/**
 * Maps a [PurchaseDto] data transfer object to a domain [Purchase] model.
 *
 * Parses the [PurchaseDto.date] string into an [Instant] and copies the [PurchaseDto.name].
 */
fun PurchaseDto.toDomain(): Purchase {
    return Purchase(
        date = Instant.parse(date),
        names = name
    )
}