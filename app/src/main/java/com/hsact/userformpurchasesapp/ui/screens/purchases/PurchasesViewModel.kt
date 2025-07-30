package com.hsact.userformpurchasesapp.ui.screens.purchases

import androidx.lifecycle.ViewModel
import com.hsact.domain.usecase.GetPurchasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchasesViewModel @Inject constructor(
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModel(){
}