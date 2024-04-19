package com.lyadsky.centr_invest_card_client.utils

sealed class LoadingState {
    data object Loading: LoadingState()

    data object Success: LoadingState()

    data object Empty: LoadingState()

    data class Error(val error: String): LoadingState()
}