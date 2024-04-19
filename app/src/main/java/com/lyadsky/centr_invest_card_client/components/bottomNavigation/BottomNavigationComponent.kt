package com.lyadsky.centr_invest_card_client.components.bottomNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.home.HomeComponent
import com.lyadsky.centr_invest_card_client.components.settings.SettingsComponent

interface BottomNavigationComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onTabClicked(tab: MainNavTab)

    fun onBackClicked(toIndex: Int)

    sealed class Child {

        data class HomeChild(val component: HomeComponent): Child()
        data class SettingsChild(val component: SettingsComponent): Child()
    }
}

enum class MainNavTab {
    HOME,
    SETTINGS
}