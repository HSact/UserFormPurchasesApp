package com.hsact.domain.usecase.userdata

import com.hsact.domain.model.UserData
import com.hsact.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving user data.
 *
 * This use case provides a stream of [UserData] from the repository.
 */
class GetUserDataUseCase(
    private val repository: UserDataRepository
) {
    /**
     * Returns a [Flow] that emits the [UserData], or `null` if not available.
     */
    operator fun invoke(): Flow<UserData?> {
        return repository.getUserData()
    }
}