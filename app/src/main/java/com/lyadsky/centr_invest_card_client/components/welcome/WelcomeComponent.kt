package com.lyadsky.centr_invest_card_client.components.welcome

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthComponent
import com.lyadsky.centr_invest_card_client.components.welcome.pincode.PincodeComponent
import com.lyadsky.centr_invest_card_client.components.welcome.registration.RegistrationComponent

interface WelcomeComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onBackButtonClick()

    sealed class Child {

        data class AuthChild(val component: AuthComponent) : Child()
        data class RegisterChild(val component: RegistrationComponent) : Child()
        data class PincodeChild(val component: PincodeComponent) : Child()
    }
}