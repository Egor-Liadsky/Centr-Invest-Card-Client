package com.turtleteam.impl.data.api.repository

import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AccountRepositoryImpl(httpClient: HttpClient) : AccountRepository,
    BaseRepository(httpClient) {

    override suspend fun registerUser(user: UserDTOReceive): User {
        val response = executeCall(
            type = HttpMethod.Post,
            path = "user/register",
            headers = mapOf("Content-Type" to "application/json"),
            parameters = mapOf(
                "username" to user.username,
                "password" to user.password,
                "name" to user.firstName,
                "family" to user.lastName,
                "two_name" to user.surname
            )
        )
        return Json.decodeFromString(response)
    }

    override suspend fun authUser(login: String, password: String): User {

        val body = """
                {
                  "username": "$login",
                  "password": "$password"
                }
            """.trimIndent()

        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/login",
            body = body,
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            ),
            parameters = mapOf(
                "username" to login,
                "password" to password,
            )
        )
        return Json.decodeFromString<User>(response)
    }
}