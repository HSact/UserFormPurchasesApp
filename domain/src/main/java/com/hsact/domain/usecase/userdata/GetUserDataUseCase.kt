package com.hsact.domain.usecase.userdata

import com.hsact.domain.model.UserData
import com.hsact.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

class GetUserDataUseCase(
    private val repository: UserDataRepository
) {
    operator fun invoke(): Flow<UserData?> {
        return repository.getUserData()
    }
}