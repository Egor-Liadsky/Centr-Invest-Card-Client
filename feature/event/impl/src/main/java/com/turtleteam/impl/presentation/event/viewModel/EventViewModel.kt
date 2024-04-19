package com.turtleteam.impl.presentation.event.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.navigation.EventRepository
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.EventNavigator
import com.turtleteam.impl.presentation.event.state.EventState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class EventViewModel(
    private val navigator: EventNavigator,
    private val rep: EventRepository,
    settings: Settings
) : ViewModel() {

    private val _state = MutableStateFlow(EventState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = Json.decodeFromString<User>(settings.getUser() ?: "")
            _state.update { it.copy(user = user) }
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(quizIds = rep.getQuizList()) }
                }
            )
        }
    }
    fun onQuizClick(id: Int){
        navigator.navigateTotQuiz(id)
    }
}