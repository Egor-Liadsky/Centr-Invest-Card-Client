package com.lyadsky.centr_invest_card_client.components.welcome.auth

import com.lyadsky.centr_invest_card_client.utils.LoadingState

data class AuthState(
    val loginTextFieldValue: String = "",
    val passwordTextFieldValue: String = "",
    val authLoadingState: LoadingState = LoadingState.Success
)