package com.hsact.userformpurchasesapp.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.model.UserData
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import com.hsact.domain.usecase.userdata.SaveUserDataUseCase
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldState
import com.hsact.userformpurchasesapp.ui.screens.registration.field.FieldType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    init {
        viewModelScope.launch {
            getUserDataUseCase()
                .filterNotNull()
                .firstOrNull()
                ?.let { userData ->
                    _uiState.value = _uiState.value.copy(
                        name = FieldState(userData.name),
                        surname = FieldState(userData.surname),
                        code = FieldState(userData.code),
                        participantNumber = FieldState(userData.participantNumber)
                    )
                }
        }
    }

    fun handleIntent(event: RegistrationIntent) {
        when (event) {
            is RegistrationIntent.NameChanged -> updateState(name = event.value)
            is RegistrationIntent.NameFocusChanged -> updateFocusState(
                FieldType.Name,
                event.isFocused
            )

            is RegistrationIntent.SurnameChanged -> updateState(surname = event.value)
            is RegistrationIntent.SurnameFocusChanged -> updateFocusState(
                FieldType.Surname,
                event.isFocused
            )

            is RegistrationIntent.CodeChanged -> updateState(code = event.value)
            is RegistrationIntent.CodeFocusChanged -> updateFocusState(
                FieldType.Code,
                event.isFocused
            )

            is RegistrationIntent.ParticipantNumberChanged -> updateState(participantNumber = event.value)
            is RegistrationIntent.ParticipantNumberFocusChanged -> updateFocusState(
                FieldType.ParticipantNumber,
                event.isFocused
            )

            is RegistrationIntent.SubmitClicked -> saveUser()
        }
    }

    private fun updateFocusState(field: FieldType, isFocused: Boolean) {
        _uiState.value = _uiState.value.run {
            when (field) {
                FieldType.Name -> copy(
                    name = name.copy(
                        isFocused = isFocused,
                        wasFocused = name.wasFocused || (!isFocused && name.isFocused)
                    )
                )

                FieldType.Surname -> copy(
                    surname = surname.copy(
                        isFocused = isFocused,
                        wasFocused = surname.wasFocused || (!isFocused && surname.isFocused)
                    )
                )

                FieldType.Code -> copy(
                    code = code.copy(
                        isFocused = isFocused,
                        wasFocused = code.wasFocused || (!isFocused && code.isFocused)
                    )
                )

                FieldType.ParticipantNumber -> copy(
                    participantNumber = participantNumber.copy(
                        isFocused = isFocused,
                        wasFocused = participantNumber.wasFocused || (!isFocused && participantNumber.isFocused)
                    )
                )
            }
        }
    }

    private fun updateState(
        name: String? = null,
        surname: String? = null,
        code: String? = null,
        participantNumber: String? = null
    ) {
        val current = _uiState.value
        _uiState.value = current.copy(
            name = name?.let { current.name.copy(text = it) } ?: current.name,
            surname = surname?.let { current.surname.copy(text = it) } ?: current.surname,
            code = code?.let { current.code.copy(text = it) } ?: current.code,
            participantNumber = participantNumber?.let { current.participantNumber.copy(text = it) }
                ?: current.participantNumber
        )
    }

    private fun saveUser() {
        viewModelScope.launch {
            val user = with(_uiState.value) {
                UserData(
                    name = name.text,
                    surname = surname.text,
                    code = code.text,
                    participantNumber = participantNumber.text
                )
            }
            saveUserDataUseCase(user)
            _uiState.value = _uiState.value.copy(isFinished = true)
        }
    }
}