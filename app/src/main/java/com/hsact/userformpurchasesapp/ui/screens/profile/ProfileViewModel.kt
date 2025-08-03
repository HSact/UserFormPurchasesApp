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

/**
 * ViewModel for the Profile screen.
 *
 * Manages the UI state related to the user's profile data,
 * including user name, surname, and biometric authentication toggle.
 *
 * @property getUserDataUseCase Use case to fetch user data from the repository.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState

    /**
     * Toggles the biometric authentication enabled state in the UI.
     */
    fun switchBiometric() {
        _uiState.value = _uiState.value.copy(
            isBiometricEnabled = !_uiState.value.isBiometricEnabled
        )
    }

    init {
        viewModelScope.launch {
            try {
                getUserDataUseCase()
                    .filterNotNull()
                    .collect { userData ->
                        _uiState.value = _uiState.value.copy(
                            name = userData.name,
                            surname = userData.surname
                        )
                    }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error fetching user data", e)
            }
        }
    }
}