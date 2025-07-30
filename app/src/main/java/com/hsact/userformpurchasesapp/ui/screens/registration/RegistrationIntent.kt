package com.hsact.userformpurchasesapp.ui.screens.registration

sealed class RegistrationIntent {
    data class NameChanged(val value: String) : RegistrationIntent()
    data class SurnameChanged(val value: String) : RegistrationIntent()
    data class CodeChanged(val value: String) : RegistrationIntent()
    data class ParticipantNumberChanged(val value: String) : RegistrationIntent()
    object SubmitClicked : RegistrationIntent()
}