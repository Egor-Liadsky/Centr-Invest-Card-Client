package com.turtleteam.api.data.api.model

import kotlinx.serialization.Serializable

data class UserDTOReceive(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val surname: String
)

@Serializable
data class User(
    val auth_hash: String,
)
