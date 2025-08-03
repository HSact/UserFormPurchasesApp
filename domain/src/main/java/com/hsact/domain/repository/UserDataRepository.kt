package com.hsact.domain.repository

import com.hsact.domain.model.UserData
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing user-related data.
 *
 * This abstracts the source of user data, whether it's local storage, network, or any other implementation.
 */
interface UserDataRepository {

    /**
     * Saves the given [UserData] to persistent storage.
     *
     * @param user The user data to save.
     */
    suspend fun saveUserData(user: UserData)

    /**
     * Returns a [Flow] that emits the current [UserData] or null if not set.
     */
    fun getUserData(): Flow<UserData?>
}