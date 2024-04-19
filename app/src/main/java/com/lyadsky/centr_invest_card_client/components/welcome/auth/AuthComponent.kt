package com.lyadsky.centr_invest_card_client.components.welcome.auth

import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthState

interface AuthComponent {

    val viewStates: Value<AuthState>

    fun loginTextFieldValueChanged(value: String)

    fun passwordTextFieldValueChanged(value: String)

    fun onRegistrationClick()

    fun showError(value: String)

    fun onPincodeClick()
}