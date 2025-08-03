package com.hsact.userformpurchasesapp.ui.screens.registration

import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldInputMaxLength
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldState

/**
 * Represents the UI state of the registration screen.
 *
 * Holds the state of each input field as [FieldState] and
 * a flag indicating whether the registration process is finished.
 *
 * @property name State of the "Name" input field.
 * @property surname State of the "Surname" input field.
 * @property code State of the "Code" input field.
 * @property participantNumber State of the "Participant Number" input field.
 * @property isFinished Flag indicating if the registration has been completed.
 */
data class RegistrationUiState(
    val name: FieldState = FieldState(),
    val surname: FieldState = FieldState(),
    val code: FieldState = FieldState(),
    val participantNumber: FieldState = FieldState(),
    val isFinished: Boolean = false
) {
    /**
     * Returns true if all required fields are valid.
     *
     * Checks that name, surname, and code are not empty,
     * and that participant number has correct length and contains only digits.
     */
    val isValid: Boolean
        get() = name.isNotEmpty &&
                surname.isNotEmpty &&
                code.isNotEmpty &&
                participantNumber.length == FieldInputMaxLength.ParticipantNumber.value &&
                participantNumber.isDigit
}