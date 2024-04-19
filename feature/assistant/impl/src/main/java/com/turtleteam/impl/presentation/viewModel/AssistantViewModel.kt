package com.turtleteam.impl.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Speaker
import com.turtleteam.api.data.api.repository.AssistantRepository
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.AssistantNavigator
import com.turtleteam.impl.repository.AssistantRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssistantViewModel(
    private val navigator: AssistantNavigator,
    private val rep: AssistantRepository,
    private val speaker: Speaker
) : ViewModel() {

    private val _state = MutableStateFlow(AssistantState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    chat = (rep as AssistantRepositoryImpl).getChat() ?: listOf(
                        Message(
                            "Привет! Чем могу помочь?",
                            Sender.ASSISTANT
                        )
                    )
                )
            }
        }
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun onMessageClick(text: String) {
        speaker.speak(text)
    }

    fun onMessageSend(text: String) {
        _state.update { it.copy(chat = it.chat + Message(text, Sender.USER),textFieldEnabled = false) }
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(chat = it.chat + Message(rep.sendMessage(text), Sender.ASSISTANT)) }
                },
                completionBlock = {
                    _state.update { it.copy(textFieldEnabled = true) }
                    (rep as AssistantRepositoryImpl).setChat(_state.value.chat)
                }
            )
        }
    }
}