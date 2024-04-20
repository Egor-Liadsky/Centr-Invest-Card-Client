package com.turtleteam.api.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: Int,
    val key: String
)

@Serializable
data class ShortPrivileges(
    val result: List<String>
)

@Serializable
data class FullPrivileges(
    val privileges_prefix: String,
    val name: String,
    val legend: String,
    val history: String,
)
