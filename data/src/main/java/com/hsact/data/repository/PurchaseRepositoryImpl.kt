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

/**
 * Implementation of [PurchaseRepository] that fetches purchases from a data source.
 *
 * @property dataSource The data source used to load purchase data.
 */
class PurchaseRepositoryImpl @Inject constructor(
    private val dataSource: PurchaseDataSource
) : PurchaseRepository {

    /**
     * Returns a flow emitting lists of [Purchase] objects loaded from assets.
     * The mapping from data source models to domain models is done here.
     * The flow operates on the IO dispatcher.
     */
    override fun getPurchases(): Flow<List<Purchase>> {
        return dataSource
            .loadFromAssets()
            .map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }
}