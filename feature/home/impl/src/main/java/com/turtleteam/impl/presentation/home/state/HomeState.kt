package com.turtleteam.impl.presentation.home.state

import com.turtleteam.api.api.model.CardShort
import com.turtleteam.api.data.api.model.User

data class HomeState(
    val cards: List<CardShort>? = null,
    val user: User? = null
)