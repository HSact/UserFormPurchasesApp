package com.hsact.userformpurchasesapp.ui.screens.registration.field

/**
 * Represents the state of a single input field on the registration screen.
 *
 * @property text Current text entered by the user.
 * @property isFocused Whether the field is currently focused.
 * @property wasFocused Whether the field was ever focused (used to trigger validation or UI effects).
 */
data class FieldState(
    val text: String = "",
    val isFocused: Boolean = false,
    val wasFocused: Boolean = false
) {
    /** Returns true if the text consists only of digits. */
    val isDigit: Boolean
        get() = text.all { it.isDigit() }

    /** Returns true if the text is not empty. */
    val isNotEmpty: Boolean
        get() = text.isNotEmpty()

    /** Returns the number of characters in the text. */
    val length: Int
        get() = text.length
}