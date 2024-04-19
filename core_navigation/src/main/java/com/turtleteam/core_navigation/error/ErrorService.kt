package com.turtleteam.core_navigation.error

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ErrorService {

    private val _state = MutableSharedFlow<String>()
    val state = _state.asSharedFlow()

    suspend fun showError(message: String) {
        _state.emit(message)
    }
}