package com.turtleteam.core_view.models

data class ServiceHistory(
    val date: String,
    val price: Float,
    val reason: String,
    val inn: Int,
    val category_id: Int,
)