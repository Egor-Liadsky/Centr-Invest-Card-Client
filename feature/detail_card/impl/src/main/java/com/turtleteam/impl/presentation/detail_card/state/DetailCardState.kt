package com.turtleteam.impl.presentation.detail_card.state

import com.turtleteam.api.data.api.model.User

data class DetailCardState(
    val isDetailsShown: Boolean = false,
    val limitBegin: Int? = null,
    val limitEnd: Int? = null,
    val user: User? = null,
    val cardDate: String ="08/28",
    val cardCvc: String = "412"
)