package com.hsact.domain.repository

import com.hsact.domain.model.Purchase
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    fun getPurchases(): Flow<List<Purchase>>
}