package com.turtleteam.api.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val date: String,
    val price: Float,
    val reason: String,
    val inn: Int,
    val category_id: Int,
)