package com.hsact.userformpurchasesapp.ui.screens.profile

data class ProfileUiState (
    val name: String = "Unknown",
    val surname: String = "",
    val email: String = "example@example.com",
    val isBiometricEnabled: Boolean = true,
    val language: String = "русский",
)