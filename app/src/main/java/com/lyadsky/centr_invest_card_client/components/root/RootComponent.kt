package com.lyadsky.centr_invest_card_client.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.centr_invest_card_client.components.welcome.WelcomeComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    val viewStates: Value<RootState>

    fun onBackButtonClick()

    sealed class Child {

        data class WelcomeChild(val component: WelcomeComponent): Child()
        data class BottomNavigationChild(val component: BottomNavigationComponent) : Child()
    }
}