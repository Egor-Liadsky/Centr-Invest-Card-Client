package com.lyadsky.centr_invest_card_client.components.root

import com.lyadsky.centr_invest_card_client.utils.LoadingState

data class RootState(

    val categoriesLoadingState: LoadingState = LoadingState.Loading
)