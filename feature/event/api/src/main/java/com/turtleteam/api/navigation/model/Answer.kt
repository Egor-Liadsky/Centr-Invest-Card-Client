package com.turtleteam.api.navigation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable //fixme
data class Answer(
    val id: Int,
    @SerialName("is_answer")
    val isAnswer: Int,
    val name: String
)