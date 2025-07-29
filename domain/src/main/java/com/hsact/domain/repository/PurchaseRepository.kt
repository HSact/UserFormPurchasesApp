package com.hsact.domain.repository

import com.hsact.domain.model.Purchase

interface PurchaseRepository {
    suspend fun getPurchases(): List<Purchase>
}