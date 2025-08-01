package com.hsact.userformpurchasesapp.ui.screens.registration

data class RegistrationUiState(
    val name: String = "",
    val surname: String = "",
    val code: String = "",
    val participantNumber: String = "",
    val isFinished: Boolean = false
) {
    val isValid: Boolean
        get() = name.isNotBlank() &&
                surname.isNotBlank() &&
                code.isNotBlank() &&
                participantNumber.length == FieldInputMaxLength.ParticipantNumber.value &&
                participantNumber.all { it.isDigit() }
}