package com.hsact.userformpurchasesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.usecase.GetPurchasesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            val a = getPurchasesUseCase()
            println(a)
        }
    }
}