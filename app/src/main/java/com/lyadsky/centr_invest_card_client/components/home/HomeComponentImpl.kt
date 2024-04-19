package com.lyadsky.centr_invest_card_client.components.home

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.centr_invest_card_client.components.BaseComponent
import org.koin.core.component.KoinComponent

class HomeComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent<HomeState>(componentContext, HomeState()), HomeComponent, KoinComponent {

}