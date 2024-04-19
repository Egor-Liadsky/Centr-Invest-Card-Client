package com.turtleteam.impl.presentation.viewModel

import kotlinx.serialization.Serializable

data class AssistantState(
    val chat: List<Message> = listOf(),
    val textFieldEnabled: Boolean = true
)

@Serializable
data class Message(
    val text: String,
    val sender: Sender
)
enum class Sender {
    USER,ASSISTANT
}