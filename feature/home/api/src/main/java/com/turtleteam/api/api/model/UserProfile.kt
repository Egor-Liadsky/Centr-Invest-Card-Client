package com.turtleteam.api.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val username: String,
    val name: String,
    @SerialName("family") val surname: String,
    @SerialName("two_name") val patronymic: String,
    val cash: Float
)