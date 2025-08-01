package com.hsact.userformpurchasesapp.ui.screens.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.model.Purchase
import com.hsact.domain.usecase.GetPurchasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.collections.flatMap

@HiltViewModel
class PurchasesViewModel @Inject constructor(
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PurchasesUiState>(PurchasesUiState.Loading)
    val uiState: StateFlow<PurchasesUiState> = _uiState

    init {
        viewModelScope.launch {
            try {
                getPurchasesUseCase().collect { list ->
                    val grouped = list
                        .sortedByDescending { it.date }
                        .groupBy {
                            it.date.atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        }
                        .mapValues { entry: Map.Entry<String, List<Purchase>> ->
                            entry.value.flatMap { it.names }
                        }
                    _uiState.value = PurchasesUiState.Success(grouped)
                }
            } catch (e: Exception) {
                _uiState.value = PurchasesUiState.Error("Failed to load purchases: ${e.localizedMessage}")
            }
        }
    }
}