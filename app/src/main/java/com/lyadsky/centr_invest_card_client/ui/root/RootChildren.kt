package com.lyadsky.centr_invest_card_client.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.centr_invest_card_client.components.root.RootComponent
import ui.bottomNavigation.BottomNavigationScreen

@Composable
fun RootChildren(component: RootComponent, modifier: Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.BottomNavigationChild -> BottomNavigationScreen(child.component)
        }
    }
}