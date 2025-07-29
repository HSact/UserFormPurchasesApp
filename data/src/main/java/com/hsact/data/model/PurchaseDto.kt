package com.hsact.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseDto(
    val date: String,
    val name: List<String>
)