package com.hsact.userformpurchasesapp.ui.screens.registration.field

/**
 * Represents the types of input fields used in the registration form.
 */
internal enum class FieldType {
    /**
     * First name field.
     */
    Name,
    /**
     * Last name (surname) field.
     */
    Surname,
    /**
     * Registration code field.
     */
    Code,
    /**
     * Participant number field.
     */
    ParticipantNumber;

    /**
     * Returns the expected [FieldInputType] for the current field.
     *
     * For example:
     * - [Name] and [Surname] expect Latin letters only.
     * - [Code] and [ParticipantNumber] expect numeric input.
     */
    val inputType: FieldInputType
        get() = when (this) {
        Name, Surname -> FieldInputType.LatinLetters
        Code, ParticipantNumber -> FieldInputType.Number
    }

    /**
     * Returns the maximum allowed input length for the current field.
     */
    val maxLength: Int
        get() = when (this) {
        Name -> FieldInputMaxLength.Name.value
        Surname -> FieldInputMaxLength.Surname.value
        Code -> FieldInputMaxLength.Code.value
        ParticipantNumber -> FieldInputMaxLength.ParticipantNumber.value
    }
}