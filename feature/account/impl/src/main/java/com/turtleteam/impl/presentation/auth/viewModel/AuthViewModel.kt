package com.turtleteam.impl.presentation.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.data.api.service.AccountService
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.AccountNavigator
import com.turtleteam.impl.presentation.auth.state.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthViewModel(
    private val navigator: AccountNavigator,
    private val accountService: AccountService,
    private val errorService: ErrorService,
    private val settings: Settings
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onLoginTextChanged(login: String) {
        _state.update { it.copy(loginText = login) }
    }

    fun onPasswordTextChanged(password: String) {
        _state.update { it.copy(passwordText = password) }
    }

    fun navigateToRegister() {
        navigator.navigateToRegister()
    }

    fun onAuthClick(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
//                    _state.update { it.copy(authLoadingState = LoadingState.Loading) }
//                    val user = accountService.authUser(login, password)
//                    settings.setToken(user.token)
//                    val userJson = Json.encodeToString(user)
//                    settings.setUser(userJson)
                    withContext(Dispatchers.Main) {
                        navigator.navigateToPincode()
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(authLoadingState = LoadingState.Error(throwable.message.toString())) }
                    errorService.showError("Неверный логин или пароль")
                },
                completionBlock = {
                    _state.update { it.copy(authLoadingState = LoadingState.Success) }
                }
            )
        }
    }
}