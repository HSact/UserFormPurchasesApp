package com.hsact.userformpurchasesapp.ui.screens.purchases

/**
 * Represents the UI state for the Purchases screen.
 */
sealed class PurchasesUiState {

    /** State when purchases are being loaded. */
    object Loading : PurchasesUiState()

    /**
     * State when purchases are successfully loaded.
     *
     * @property groupedPurchases Map where the key is a date string and the value is a list of purchase names.
     */
    data class Success(val groupedPurchases: Map<String, List<String>>) : PurchasesUiState()

    /**
     * State when there is an error loading purchases.
     *
     * @param message Error message to display.
     */
    data class Error(val message: String) : PurchasesUiState()
}