package com.hsact.userformpurchasesapp.ui.screens.registration

import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldInputMaxLength
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldState

data class RegistrationUiState(
    val name: FieldState = FieldState(),
    val surname: FieldState = FieldState(),
    val code: FieldState = FieldState(),
    val participantNumber: FieldState = FieldState(),
    val isFinished: Boolean = false
) {
    val isValid: Boolean
        get() = name.isNotEmpty &&
                surname.isNotEmpty &&
                code.isNotEmpty &&
                participantNumber.length == FieldInputMaxLength.ParticipantNumber.value &&
                participantNumber.isDigit
}