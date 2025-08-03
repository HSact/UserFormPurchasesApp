package com.hsact.userformpurchasesapp.ui.screens.registration.field

internal enum class FieldType {
    Name,
    Surname,
    Code,
    ParticipantNumber;

    val inputType: FieldInputType
        get() = when (this) {
        Name, Surname -> FieldInputType.LatinLetters
        Code, ParticipantNumber -> FieldInputType.Number
    }

    val maxLength: Int
        get() = when (this) {
        Name -> FieldInputMaxLength.Name.value
        Surname -> FieldInputMaxLength.Surname.value
        Code -> FieldInputMaxLength.Code.value
        ParticipantNumber -> FieldInputMaxLength.ParticipantNumber.value
    }
}