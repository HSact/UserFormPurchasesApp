package com.hsact.domain.usecase

import com.hsact.domain.model.Purchase
import com.hsact.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow

class GetPurchasesUseCase(
    private val repository: PurchaseRepository
) {
    operator fun invoke(): Flow<List<Purchase>> {
        return repository.getPurchases()
    }
}