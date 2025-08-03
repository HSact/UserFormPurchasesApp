package com.hsact.data.repository

import com.hsact.data.datasource.DataStoreManager
import com.hsact.domain.model.UserData
import com.hsact.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [UserDataRepository] that delegates data operations to [DataStoreManager].
 *
 * This repository provides an abstraction over the data layer for working with [UserData].
 *
 * @property dataStoreManager The data source responsible for actual persistence.
 */
class UserDataRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : UserDataRepository {

    /**
     * Saves user data via [DataStoreManager].
     */
    override suspend fun saveUserData(user: UserData) {
        dataStoreManager.saveUserData(user)
    }

    /**
     * Retrieves user data as a [Flow] from [DataStoreManager].
     */
    override fun getUserData(): Flow<UserData?> {
        return dataStoreManager.getUserData()
    }
}