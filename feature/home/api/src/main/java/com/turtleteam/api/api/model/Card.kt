package com.turtleteam.api.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: Int,
    val key: String
)