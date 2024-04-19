package com.lyadsky.centr_invest_card_client.components.welcome

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.BaseComponent
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthComponentImpl
import com.lyadsky.centr_invest_card_client.components.welcome.pincode.PincodeComponentImpl
import com.lyadsky.centr_invest_card_client.components.welcome.registration.RegistrationComponentImpl
import kotlinx.serialization.Serializable

class WelcomeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToHome: () -> Unit
) : BaseComponent<Unit>(componentContext, Unit), WelcomeComponent {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, WelcomeComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Auth,
            childFactory = ::childFactory
        )

    override fun onBackButtonClick() {
        navigation.pop()
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext
    ): WelcomeComponent.Child =
        when (config) {
            Config.Auth -> authComponent(componentContext)
            Config.Pincode -> pincodeComponent(componentContext)
            Config.Registration -> registrationComponent(componentContext)
        }

    private fun authComponent(componentContext: ComponentContext): WelcomeComponent.Child =
        WelcomeComponent.Child.AuthChild(
            AuthComponentImpl(
                componentContext = componentContext,
                navigateToRegistration = { navigation.bringToFront(Config.Registration) },
                navigateToPincode = { navigation.push(Config.Pincode) }
            )
        )

    private fun registrationComponent(componentContext: ComponentContext): WelcomeComponent.Child =
        WelcomeComponent.Child.RegisterChild(
            RegistrationComponentImpl(
                componentContext = componentContext,
                backButtonClick = { onBackButtonClick() },
                navigateToPincode = { navigation.push(Config.Pincode) }
            )
        )

    private fun pincodeComponent(componentContext: ComponentContext): WelcomeComponent.Child =
        WelcomeComponent.Child.PincodeChild(
            PincodeComponentImpl(componentContext = componentContext,
                navigateToHome = { navigateToHome() })
        )

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Auth : Config

        @Serializable
        data object Registration : Config

        @Serializable
        data object Pincode : Config
    }
}