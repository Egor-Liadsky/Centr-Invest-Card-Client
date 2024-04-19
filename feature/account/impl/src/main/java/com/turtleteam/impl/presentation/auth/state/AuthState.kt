package com.turtleteam.impl.presentation.auth.state

import com.turtleteam.core_navigation.state.LoadingState

data class AuthState(
    val loginText: String = "",
    val passwordText: String = "",
    val authLoadingState: LoadingState = LoadingState.Success
)