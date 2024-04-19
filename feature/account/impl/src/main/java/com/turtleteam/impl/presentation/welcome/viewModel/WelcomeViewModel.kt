package com.turtleteam.impl.presentation.welcome.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.AccountNavigator

class WelcomeViewModel(private val navigator: AccountNavigator): ViewModel() {

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onAuthClick() {
        navigator.navigateToAuth()
    }

    fun onRegisterClick() {
        navigator.navigateToRegister()
    }
}