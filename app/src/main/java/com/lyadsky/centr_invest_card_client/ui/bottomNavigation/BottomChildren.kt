package com.lyadsky.centr_invest_card_client.ui.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.centr_invest_card_client.ui.home.HomeScreen
import com.lyadsky.centr_invest_card_client.ui.settings.SettingsScreen

@Composable
fun BottomNavigationChildren(
    component: BottomNavigationComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is BottomNavigationComponent.Child.HomeChild -> HomeScreen(child.component)
            is BottomNavigationComponent.Child.SettingsChild -> SettingsScreen(child.component)
        }
    }
}