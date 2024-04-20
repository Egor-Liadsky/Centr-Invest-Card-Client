package com.turtleteam.api.api.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    val result: UserProfile
)