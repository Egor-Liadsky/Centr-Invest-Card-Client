package com.whatrushka.api.profile.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val nickname: String = ""
)
