package com.turtleteam.impl.presentation.event.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.navigation.EventRepository
import com.turtleteam.api.navigation.model.Quiz
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.EventNavigator
import com.turtleteam.impl.presentation.event.state.EventState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class QuizState(
    val loadingState: LoadingState = LoadingState.Loading,
    val quiz: Quiz? = null,
    val currentStage: Int = 1,
)

class QuizViewModel(
    private val navigator: EventNavigator,
    private val quizId: Int,
    private val rep: EventRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(QuizState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update {
                        it.copy(
                            quiz = rep.getQuizList().find { it.quizId == quizId },
                            loadingState = LoadingState.Success
                        )
                    }
                },
                failureBlock = { e ->
                    _state.update { it.copy(loadingState = LoadingState.Error(e.message ?: "")) }
                }
            )
        }
    }
    fun onBackClick(){
        navigator.navigateBack()
    }
}