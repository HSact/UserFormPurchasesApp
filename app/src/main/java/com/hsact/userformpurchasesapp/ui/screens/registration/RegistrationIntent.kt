package com.hsact.userformpurchasesapp.ui.screens.registration

/**
 * Represents user actions (intents) on the registration screen.
 * These intents are handled by the ViewModel to update the UI state.
 */
sealed interface RegistrationIntent {
    data class NameChanged(val value: String) : RegistrationIntent
    data class NameFocusChanged(val isFocused: Boolean) : RegistrationIntent

    data class SurnameChanged(val value: String) : RegistrationIntent
    data class SurnameFocusChanged(val isFocused: Boolean) : RegistrationIntent

    data class CodeChanged(val value: String) : RegistrationIntent
    data class CodeFocusChanged(val isFocused: Boolean) : RegistrationIntent

    data class ParticipantNumberChanged(val value: String) : RegistrationIntent
    data class ParticipantNumberFocusChanged(val isFocused: Boolean) : RegistrationIntent

    object SubmitClicked : RegistrationIntent
}