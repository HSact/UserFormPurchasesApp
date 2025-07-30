package com.hsact.domain.usecase

import com.hsact.domain.model.Purchase
import com.hsact.domain.repository.PurchaseRepository

class GetPurchasesUseCase(
    private val repository: PurchaseRepository
) {
    suspend operator fun invoke(): List<Purchase> {
        return repository.getPurchases()
    }
}