package com.hsact.data.repository

import com.hsact.data.datasource.DataStoreManager
import com.hsact.domain.model.UserData
import com.hsact.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : UserDataRepository {

    override suspend fun saveUserData(user: UserData) {
        dataStoreManager.saveUserData(user)
    }

    override fun getUserData(): Flow<UserData?> {
        return dataStoreManager.getUserData()
    }
}