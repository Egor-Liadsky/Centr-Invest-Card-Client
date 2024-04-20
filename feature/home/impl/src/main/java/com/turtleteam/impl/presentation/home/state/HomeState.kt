package com.turtleteam.impl.presentation.home.state

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.data.api.model.User

data class HomeState(
    val cards: List<Card>? = null,
    val user: User? = null
)