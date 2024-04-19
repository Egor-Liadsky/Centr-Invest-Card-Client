package com.lyadsky.centr_invest_card_client.ui.bottomNavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.BottomNavigationComponent.Child
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.MainNavTab
import com.lyadsky.centr_invest_card_client.ui.theme.Color

@Composable
fun BottomBar(component: BottomNavigationComponent, modifier: Modifier = Modifier) {

    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color.BottomBar.stroke
    )

    BottomNavigation(
        modifier = modifier.height(60.dp),
        backgroundColor = Color.BottomBar.background,
        elevation = 0.dp,
    ) {

        BottomNavigationItem(
            selected = activeComponent is Child.HomeChild,
            onClick = { component.onTabClicked(MainNavTab.HOME) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text("Главная")
            }
        )
        BottomNavigationItem(
            selected = activeComponent is Child.SettingsChild,
            onClick = { component.onTabClicked(MainNavTab.SETTINGS) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Пользователи",
                    Modifier.size(24.dp)
                )
            },
            label = {
                Text("Настройки")
            }
        )
    }
}