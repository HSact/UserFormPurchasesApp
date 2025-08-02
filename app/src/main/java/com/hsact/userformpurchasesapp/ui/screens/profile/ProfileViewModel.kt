package com.hsact.userformpurchasesapp.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState
    fun switchBiometric() {
        _uiState.value = _uiState.value.copy(
            isBiometricEnabled = !_uiState.value.isBiometricEnabled
        )
    }

    init {
        viewModelScope.launch {
            getUserDataUseCase()
                .filterNotNull()
                .collect { userData ->
                    Log.d("ProfileViewModel", "Got userData: $userData")
                    _uiState.value = _uiState.value.copy(
                        name = userData.name,
                        surname = userData.surname,
                        email = _uiState.value.email,
                        isBiometricEnabled = _uiState.value.isBiometricEnabled,
                        language = _uiState.value.language
                    )
                }
        }
    }
}