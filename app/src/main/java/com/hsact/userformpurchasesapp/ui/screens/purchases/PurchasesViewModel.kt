package com.hsact.userformpurchasesapp.ui.screens.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.usecase.purchases.GetGroupedPurchasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the UI state of the purchases screen.
 *
 * It loads and formats grouped purchases using [GetGroupedPurchasesUseCase].
 * Each group is mapped by a formatted date string (e.g., "01.08.2025") to a list of purchases.
 *
 * The UI observes [uiState] to render loading, success, or error states.
 *
 * @property getGroupedPurchasesUseCase Use case to retrieve purchases grouped by date.
 */
@HiltViewModel
class PurchasesViewModel @Inject constructor(
    private val getGroupedPurchasesUseCase: GetGroupedPurchasesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PurchasesUiState>(PurchasesUiState.Loading)
    val uiState: StateFlow<PurchasesUiState> = _uiState

    private val dateFormatter = java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")

    init {
        viewModelScope.launch {
            try {
                getGroupedPurchasesUseCase().collect { grouped ->
                    val formatted = grouped.mapKeys { (localDate, _) ->
                        localDate.format(dateFormatter)
                    }
                    _uiState.value = PurchasesUiState.Success(formatted)
                }
            } catch (e: Exception) {
                _uiState.value =
                    PurchasesUiState.Error("Failed to load purchases: ${e.localizedMessage}")
            }
        }
    }
}