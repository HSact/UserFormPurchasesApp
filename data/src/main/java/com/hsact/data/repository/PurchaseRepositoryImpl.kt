package com.hsact.data.repository

import com.hsact.data.datasource.PurchaseDataSource
import com.hsact.data.mapper.toDomain
import com.hsact.domain.model.Purchase
import com.hsact.domain.repository.PurchaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val dataSource: PurchaseDataSource
) : PurchaseRepository {

    override fun getPurchases(): Flow<List<Purchase>> {
        return dataSource
            .loadFromAssets()
            .map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }
}