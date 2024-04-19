package com.lyadsky.centr_invest_card_client.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.BaseComponent
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.BottomNavigationComponentComponentImpl
import com.lyadsky.centr_invest_card_client.components.welcome.WelcomeComponentImpl
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

class RootComponentImpl(
    componentContext: ComponentContext
) : BaseComponent<RootState>(componentContext, RootState()), RootComponent, KoinComponent {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Welcome,
            childFactory = ::childFactory
        )

    override fun onBackButtonClick() {
        navigation.pop()
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            Config.BottomNavigation -> bottomNavigationComponent(componentContext)
            Config.Welcome -> welcomeComponent(componentContext)
        }

    private fun bottomNavigationComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.BottomNavigationChild(
            BottomNavigationComponentComponentImpl(componentContext = componentContext)
        )

    private fun welcomeComponent(componentContext: ComponentContext): RootComponent.Child =
        RootComponent.Child.WelcomeChild(
            WelcomeComponentImpl(componentContext = componentContext,
                navigateToHome = { navigation.bringToFront(Config.BottomNavigation) })
        )

    @Serializable
    private sealed interface Config {

        @Serializable
        data object BottomNavigation : Config

        @Serializable
        data object Welcome: Config
    }
}