package com.lyadsky.centr_invest_card_client.components.welcome.registration

import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthState

interface RegistrationComponent {

    val viewStates: Value<RegistrationState>

    fun firstNameTextFieldValueChanged(value: String)

    fun lastNameTextFieldValueChanged(value: String)

    fun surnameTextFieldValueChanged(value: String)

    fun loginTextFieldValueChanged(value: String)

    fun passwordTextFieldValueChanged(value: String)

    fun showError(value: String)

    fun onBackButtonClick()

    fun onPincodeClick()
}