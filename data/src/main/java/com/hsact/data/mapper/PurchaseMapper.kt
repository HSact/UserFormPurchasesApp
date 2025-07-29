package com.hsact.data.mapper

import com.hsact.data.model.PurchaseDto
import com.hsact.domain.model.Purchase
import java.time.Instant

fun PurchaseDto.toDomain(): Purchase {
    return Purchase(
        date = Instant.parse(date),
        names = name
    )
}