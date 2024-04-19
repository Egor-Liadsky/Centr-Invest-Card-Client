package com.lyadsky.centr_invest_card_client.ui.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.centr_invest_card_client.components.root.RootComponent
import ui.bottomNavigation.BottomNavigationScreen

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.viewStates.subscribeAsState()

    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))

            RootChildren(component = component, modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}