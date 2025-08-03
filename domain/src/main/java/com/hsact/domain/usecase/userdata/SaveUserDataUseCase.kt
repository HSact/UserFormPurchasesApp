package com.hsact.domain.usecase.userdata

import com.hsact.domain.model.UserData
import com.hsact.domain.repository.UserDataRepository

/**
 * Use case for saving user data to the repository.
 *
 * This use case handles persisting participant information such as number,
 * name, surname, and code via the [UserDataRepository].
 *
 * @property repository The repository used to persist [UserData].
 */
class SaveUserDataUseCase(
    private val repository: UserDataRepository
) {
    /**
     * Saves the given [UserData] using the repository.
     *
     * @param user The [UserData] object containing the participant's information.
     */
    suspend operator fun invoke(user: UserData) {
        repository.saveUserData(user)
    }
}