package com.hsact.userformpurchasesapp.ui.screens.purchases

sealed class PurchasesUiState {
    object Loading : PurchasesUiState()
    data class Success(val groupedPurchases: Map<String, List<String>>) : PurchasesUiState()
    data class Error(val message: String) : PurchasesUiState()
}