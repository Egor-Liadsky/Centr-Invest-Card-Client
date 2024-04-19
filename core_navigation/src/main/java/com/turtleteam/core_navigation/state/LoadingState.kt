package com.turtleteam.core_navigation.state

sealed class LoadingState {
    data object Loading: LoadingState()
    data object Success: LoadingState()
    data object Empty: LoadingState()
    data class Error(val error: String): LoadingState()
}
