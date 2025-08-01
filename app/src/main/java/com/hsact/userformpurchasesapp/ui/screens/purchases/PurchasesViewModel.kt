package com.hsact.userformpurchasesapp.ui.screens.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.usecase.purchases.GetGroupedPurchasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchasesViewModel @Inject constructor(
    private val getGroupedPurchasesUseCase: GetGroupedPurchasesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PurchasesUiState>(PurchasesUiState.Loading)
    val uiState: StateFlow<PurchasesUiState> = _uiState

    init {
        viewModelScope.launch {
            try {
                getGroupedPurchasesUseCase().collect { grouped ->
                    _uiState.value = PurchasesUiState.Success(grouped)
                }
            } catch (e: Exception) {
                _uiState.value = PurchasesUiState.Error("Failed to load purchases: ${e.localizedMessage}")
            }
        }
    }
}