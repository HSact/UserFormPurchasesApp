package com.hsact.userformpurchasesapp.ui.screens.registration.field

data class FieldState(
    val text: String = "",
    val isFocused: Boolean = false,
    val wasFocused: Boolean = false
) {
    val isDigit: Boolean
        get() = text.all { it.isDigit() }

    val isNotEmpty: Boolean
        get() = text.isNotEmpty()

    val length: Int
        get() = text.length
}