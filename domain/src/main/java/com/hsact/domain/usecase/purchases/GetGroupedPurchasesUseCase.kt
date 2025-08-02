package com.hsact.domain.usecase.purchases

import com.hsact.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId

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