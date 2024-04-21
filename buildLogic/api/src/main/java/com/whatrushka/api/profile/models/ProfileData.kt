package com.whatrushka.api.profile.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    val username: String = "",
    val name: String = "",
    val family: String = "",
    val two_name: String = "",
    val cash: Float = 0f
)
