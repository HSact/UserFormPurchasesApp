package com.hsact.data.repository

import com.hsact.data.datasource.PurchaseDataSource
import com.hsact.data.mapper.toDomain
import com.hsact.domain.model.Purchase
import com.hsact.domain.repository.PurchaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val dataSource: PurchaseDataSource
) : PurchaseRepository {

    override suspend fun getPurchases(): List<Purchase> {
        return withContext(Dispatchers.IO) {
            dataSource.loadFromAssets().map { it.toDomain() }
        }
    }
}