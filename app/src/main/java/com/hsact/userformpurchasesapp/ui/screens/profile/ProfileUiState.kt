package com.hsact.userformpurchasesapp.ui.screens.profile

/**
 * UI state data class for the Profile screen.
 *
 * @property name User's first name.
 * @property surname User's last name.
 * @property email User's email address.
 * @property isBiometricEnabled Indicates whether biometric authentication is enabled.
 * @property language Selected language for the app interface.
 */
data class ProfileUiState(
    val name: String = "",
    val surname: String = "",
    val email: String = "example@example.com",
    val isBiometricEnabled: Boolean = true,
    val language: String = "русский",
)