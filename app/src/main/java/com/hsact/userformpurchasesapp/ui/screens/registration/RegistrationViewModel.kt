package com.hsact.userformpurchasesapp.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsact.domain.model.UserData
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import com.hsact.domain.usecase.userdata.SaveUserDataUseCase
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
) : ViewModel(){
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    init {
        viewModelScope.launch {
            getUserDataUseCase()
                .filterNotNull()
                .firstOrNull()
                ?.let { userData ->
                    _uiState.value = _uiState.value.copy(
                        name = userData.name,
                        surname = userData.surname,
                        code = userData.code,
                        participantNumber = userData.participantNumber,
                        isValid = validate(
                            userData.name,
                            userData.surname,
                            userData.code,
                            userData.participantNumber
                        )
                    )
                }
        }
    }

    fun handleIntent(event: RegistrationIntent) {
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

    private fun validate(
        name: String,
        surname: String,
        code: String,
        participantNumber: String
    ): Boolean {
        return name.isNotBlank() &&
                surname.isNotBlank() &&
                code.isNotBlank() &&
                participantNumber.length == 16 &&
                participantNumber.all { it.isDigit() }
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
            _uiState.value = _uiState.value.copy(isFinished = true)
        }
    }
}