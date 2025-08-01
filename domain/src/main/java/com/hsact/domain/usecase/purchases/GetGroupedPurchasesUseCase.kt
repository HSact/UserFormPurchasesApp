package com.hsact.domain.usecase.purchases

import com.hsact.domain.model.Purchase
import com.hsact.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class GetGroupedPurchasesUseCase(
    private val repository: PurchaseRepository
) {
    operator fun invoke(): Flow<Map<String, List<String>>> {
        return repository.getPurchases()
            .map { list ->
                list
                    .sortedByDescending { it.date }
                    .groupBy {
                        it.date.atZone(ZoneId.systemDefault()).toLocalDate()
                            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    }
                    .mapValues { entry: Map.Entry<String, List<Purchase>> ->
                        entry.value.flatMap { it.names }
                    }
            }
    }
}