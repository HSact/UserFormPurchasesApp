package com.hsact.domain.usecase.purchases

import com.hsact.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId

/**
 * Use case that retrieves purchases from the repository and groups them by local date.
 *
 * Purchases are first sorted in descending order by timestamp.
 * Then they are grouped by the date (converted to the system's default time zone),
 * and for each date, a flat list of purchase names is produced.
 *
 * @property repository The purchase repository providing raw purchase data.
 * @return A [Flow] emitting a map of [LocalDate] to a list of purchase names.
 */
class GetGroupedPurchasesUseCase(
    private val repository: PurchaseRepository
) {
    operator fun invoke(): Flow<Map<LocalDate, List<String>>> {
        return repository.getPurchases()
            .map { list ->
                list
                    .sortedByDescending { it.date }
                    .groupBy {
                        it.date.atZone(ZoneId.systemDefault()).toLocalDate()
                    }
                    .mapValues { entry ->
                        entry.value.flatMap { it.names }
                    }
            }
    }
}