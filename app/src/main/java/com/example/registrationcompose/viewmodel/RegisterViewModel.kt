package com.example.registrationcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrationcompose.utils.DataStoreUtils
import com.example.registrationcompose.utils.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userDataStore: UserDataStore
) : ViewModel() {

    fun saveUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            DataStoreUtils.saveData(userDataStore.dataStore, "name", name)
            DataStoreUtils.saveData(userDataStore.dataStore, "email", email)
            DataStoreUtils.saveData(userDataStore.dataStore, "password", password)
        }
    }

    private fun checkCredentials(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val savedEmail = DataStoreUtils.getData(userDataStore.dataStore, "email", "")
                .first()
            val savedPassword = DataStoreUtils.getData(userDataStore.dataStore, "password", "")
                .first()

            val isValid = (email == savedEmail && password == savedPassword)
            onResult(isValid)
        }
    }

    fun onLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        checkCredentials(email, password) { isValid ->
            if (isValid) {
                onSuccess()
            } else {
                onError()
            }
        }
    }
}