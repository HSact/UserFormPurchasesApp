package com.hsact.userformpurchasesapp.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.usecase.GetPurchasesUseCase
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getPurchasesUseCase: GetPurchasesUseCase
) : ViewModel(){
    init {
        viewModelScope.launch {
            val a = getPurchasesUseCase().first()
            println(a)
        }
        viewModelScope.launch {
            val a = getUserDataUseCase().first()
            println(a)
        }
    }
}