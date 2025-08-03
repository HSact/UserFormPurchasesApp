package com.hsact.domain.repository

import com.hsact.domain.model.Purchase
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for accessing purchases data.
 *
 * This abstracts the source of purchases, such as a local JSON file, database, or remote API.
 */
interface PurchaseRepository {

    /**
     * Returns a [Flow] that emits the list of [Purchase] items.
     *
     * The implementation may load the data from a local or remote source.
     */
    fun getPurchases(): Flow<List<Purchase>>
}