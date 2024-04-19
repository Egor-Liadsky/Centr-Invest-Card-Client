package com.turtleteam.impl.presentation.event.state

import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.navigation.model.Quiz

data class EventState(
    val user: User? = null,
    val quizIds: List<Quiz> = listOf()
)