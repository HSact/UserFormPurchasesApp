package com.hsact.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseListDto(
    val data: List<PurchaseDto>
)