package com.turtleteam.api.navigation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quiz(
    val name: String,
    val answers: List<Answer>,
    @SerialName("quiz_id")
    val quizId: Int,
    @SerialName("teaching_text")
    val teachingText: String
)