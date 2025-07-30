package com.hsact.domain.repository

import com.hsact.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    suspend fun saveUserData(user: UserData)
    fun getUserData(): Flow<UserData?>
}