package com.hsact.userformpurchasesapp.ui.screens.registration.field

/**
 * Represents the type of input allowed in a registration field.
 *
 * - [Number]: Only numeric input allowed.
 * - [LatinLetters]: Only Latin letters and some special characters allowed.
 */
internal enum class FieldInputType {
    Number,
    LatinLetters
}