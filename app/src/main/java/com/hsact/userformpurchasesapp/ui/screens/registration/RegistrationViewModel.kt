package com.hsact.userformpurchasesapp.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.model.UserData
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import com.hsact.domain.usecase.userdata.SaveUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    fun onEvent(event: RegistrationIntent) {
        when (event) {
            is RegistrationIntent.NameChanged -> updateState(name = event.value)
            is RegistrationIntent.SurnameChanged -> updateState(surname = event.value)
            is RegistrationIntent.CodeChanged -> updateState(code = event.value)
            is RegistrationIntent.ParticipantNumberChanged -> updateState(participantNumber = event.value)
            is RegistrationIntent.SubmitClicked -> saveUser()
        }
    }

    private fun updateState(
        name: String? = null,
        surname: String? = null,
        code: String? = null,
        participantNumber: String? = null
    ) {
        val current = _uiState.value
        val newState = current.copy(
            name = name ?: current.name,
            surname = surname ?: current.surname,
            code = code ?: current.code,
            participantNumber = participantNumber ?: current.participantNumber
        )
        _uiState.value = newState.copy(
            isValid = validate(newState)
        )
    }

    private fun validate(state: RegistrationUiState): Boolean {
        return state.name.isNotBlank() &&
                state.surname.isNotBlank() &&
                state.code.isNotBlank() &&
                state.participantNumber.length == 16 &&
                state.participantNumber.all { it.isDigit() }
    }

    private fun saveUser() {
        viewModelScope.launch {
            val user = with(_uiState.value) {
                UserData(
                    name = name,
                    surname = surname,
                    code = code,
                    participantNumber = participantNumber
                )
            }
            saveUserDataUseCase(user)
        }
    }
}