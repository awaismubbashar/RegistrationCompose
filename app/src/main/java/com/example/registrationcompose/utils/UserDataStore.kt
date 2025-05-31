package com.example.registrationcompose.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(context: Context) {
    private val Context.dataStore by preferencesDataStore("user_prefs")
    private val dataStore = context.dataStore

    suspend fun saveUser(name: String, email: String, password: String) {
        dataStore.edit { prefs ->
            prefs[UserPreferenceKeys.NAME] = name
            prefs[UserPreferenceKeys.EMAIL] = email
            prefs[UserPreferenceKeys.PASSWORD] = password
        }
    }

    val userFlow: Flow<Triple<String?, String?, String?>> = dataStore.data.map { prefs ->
        Triple(
            prefs[UserPreferenceKeys.NAME],
            prefs[UserPreferenceKeys.EMAIL],
            prefs[UserPreferenceKeys.PASSWORD]
        )
    }
}