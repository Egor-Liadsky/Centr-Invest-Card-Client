package com.turtleteam.api.api.model

import com.soywiz.klock.DateTimeTz
import com.turtleteam.core_network.utils.DateTimeTzSerializer
import kotlinx.serialization.Serializable

@Serializable
data class Service(
    @Serializable(with = DateTimeTzSerializer::class)
    val date: DateTimeTz,
    val price: Float,
    val reason: String,
    val inn: Int,
    val category_id: Int,
) {
    private val date2: String = date.format("dd.MM.yyyy")
    private val time: String = date.format("HH:mm")

    val dateTime = "$date2 в $time"
}