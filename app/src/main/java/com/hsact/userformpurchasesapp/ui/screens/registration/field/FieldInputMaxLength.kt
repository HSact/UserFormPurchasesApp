package com.hsact.userformpurchasesapp.ui.screens.registration.field

/**
 * Defines the maximum allowed input length for each registration field.
 *
 * @property value Maximum number of characters allowed for the field.
 */
internal enum class FieldInputMaxLength(val value: Int) {
    ParticipantNumber(16),
    Code(6),
    Name(32),
    Surname(32)
}