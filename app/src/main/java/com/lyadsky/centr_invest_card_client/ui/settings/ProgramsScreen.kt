package com.lyadsky.centr_invest_card_client.ui.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.centr_invest_card_client.components.home.HomeComponent
import com.lyadsky.centr_invest_card_client.components.settings.SettingsComponent

@Composable
fun SettingsScreen(component: SettingsComponent) {

    val state by component.viewStates.subscribeAsState()

    Text("Settings Screen")
}
