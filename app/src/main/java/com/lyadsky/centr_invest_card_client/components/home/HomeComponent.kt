package com.lyadsky.centr_invest_card_client.components.home

import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.home.HomeState

interface HomeComponent {

    val viewStates: Value<HomeState>
}