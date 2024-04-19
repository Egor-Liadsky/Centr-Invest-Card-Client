package com.lyadsky.centr_invest_card_client.data.repository.welcome

import com.lyadsky.centr_invest_card_client.data.model.UserDTOReceive
import com.lyadsky.centr_invest_card_client.data.model.UserDTOResponse
import com.lyadsky.centr_invest_card_client.data.repository.BaseRepository
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class WelcomeRepository: BaseRepository() {

    suspend fun registerUser(user: UserDTOReceive): UserDTOResponse {
        val response = executeCall(
            type = HttpMethod.Post,
            path = "user/register",
            parameters = mapOf(
                "username" to user.username,
                "password" to user.password,
                "name" to user.name,
                "family" to user.family,
                "two_name" to user.two_name,
            )
        )
        return Json.decodeFromString(response)
    }

    suspend fun authUser(login: String, password: String): UserDTOResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/login",
            parameters = mapOf(
                "username" to login,
                "password" to password,
            )
        )
        return Json.decodeFromString(response)
    }
}