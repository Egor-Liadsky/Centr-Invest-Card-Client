package com.lyadsky.centr_invest_card_client.components.welcome.registration

import com.lyadsky.centr_invest_card_client.utils.LoadingState

data class RegistrationState(
    val lastNameTextFieldValue: String = "",
    val firstNameTextFieldValue: String = "",
    val surnameTextFieldValue: String = "",
    val loginTextFieldValue: String = "",
    val passwordTextFieldValue: String = "",
    val registrationLoadingState: LoadingState = LoadingState.Success
)