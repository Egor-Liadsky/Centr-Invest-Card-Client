package com.turtleteam.impl.presentation.register.state

import com.turtleteam.core_navigation.state.LoadingState

data class RegisterState(
    val registerLoadingState: LoadingState = LoadingState.Success,
    val checkBoxEnabled: Boolean = false,

    //Textfields
    val loginText: String = "",
    val loginError: Boolean = false,

    val firstNameText: String = "",
    val firstNameError: Boolean = false,

    val lastNameText: String = "",
    val lastNameError: Boolean = false,

    val surnameText: String = "",
    val surnameError: Boolean = false,

    val passwordText: String = "",
    val passwordError: Boolean = false,
    val isPasswordHidden: Boolean = true
)