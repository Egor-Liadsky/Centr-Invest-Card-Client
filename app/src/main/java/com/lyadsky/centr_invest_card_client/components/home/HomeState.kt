package com.lyadsky.centr_invest_card_client.components.home

import com.lyadsky.centr_invest_card_client.utils.LoadingState

data class HomeState(
    val productsLoadingState: LoadingState = LoadingState.Loading,
)