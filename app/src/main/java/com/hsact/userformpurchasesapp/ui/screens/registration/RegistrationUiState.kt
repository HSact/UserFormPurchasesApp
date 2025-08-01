package com.hsact.userformpurchasesapp.ui.screens.registration

data class RegistrationUiState(
    val name: String = "",
    val surname: String = "",
    val code: String = "",
    val participantNumber: String = "",
    val isValid: Boolean = false,
    val isFinished: Boolean = false
)