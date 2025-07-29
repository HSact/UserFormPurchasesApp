package com.hsact.domain.model

import java.time.Instant

data class Purchase(
    val date: Instant,
    val names: List<String>
)