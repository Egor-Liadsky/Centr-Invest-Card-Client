package com.lyadsky.centr_invest_card_client.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTOReceive(
    val username: String,
    val password: String,
    val name: String,
    val family: String,
    val two_name: String
)

@Serializable
data class UserDTOResponse(
    val auth_hash: String
)