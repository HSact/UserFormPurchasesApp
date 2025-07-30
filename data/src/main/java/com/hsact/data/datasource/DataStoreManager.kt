package com.hsact.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hsact.domain.model.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {

    private val Context.dataStore by preferencesDataStore(name = "user_data")

    private object Keys {
        val PARTICIPANT_NUMBER = stringPreferencesKey("participant_number")
        val CODE = stringPreferencesKey("code")
        val NAME = stringPreferencesKey("name")
        val SURNAME = stringPreferencesKey("surname")
    }

    suspend fun saveUserData(user: UserData) {
        context.dataStore.edit { prefs ->
            prefs[Keys.PARTICIPANT_NUMBER] = user.participantNumber
            prefs[Keys.CODE] = user.code
            prefs[Keys.NAME] = user.name
            prefs[Keys.SURNAME] = user.surname
        }
    }

    fun getUserData(): Flow<UserData?> {
        return context.dataStore.data.map { prefs ->
            val number = prefs[Keys.PARTICIPANT_NUMBER] ?: return@map null
            val code = prefs[Keys.CODE] ?: ""
            val name = prefs[Keys.NAME] ?: ""
            val surname = prefs[Keys.SURNAME] ?: ""

            UserData(
                participantNumber = number,
                code = code,
                name = name,
                surname = surname
            )
        }
    }
}