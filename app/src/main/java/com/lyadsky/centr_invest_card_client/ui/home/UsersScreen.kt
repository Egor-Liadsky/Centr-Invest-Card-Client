package com.lyadsky.centr_invest_card_client.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.centr_invest_card_client.components.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {

    val state by component.viewStates.subscribeAsState()

    Text("Home Screen")
}