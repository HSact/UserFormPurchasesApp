package com.hsact.domain.usecase.userdata

import com.hsact.domain.model.UserData
import com.hsact.domain.repository.UserDataRepository

class SaveUserDataUseCase(
    private val repository: UserDataRepository
) {
    suspend operator fun invoke(user: UserData) {
        repository.saveUserData(user)
    }
}