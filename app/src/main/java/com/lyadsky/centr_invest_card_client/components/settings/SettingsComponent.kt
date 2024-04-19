package com.lyadsky.centr_invest_card_client.components.settings

import com.arkivanov.decompose.value.Value
import com.lyadsky.centr_invest_card_client.components.settings.SettingsState

interface SettingsComponent {

    val viewStates: Value<SettingsState>
}