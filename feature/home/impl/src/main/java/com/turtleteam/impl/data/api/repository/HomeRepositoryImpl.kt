package com.turtleteam.impl.data.api.repository

import com.turtleteam.api.Settings
import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.model.ShortPrivileges
import com.turtleteam.api.api.model.UserProfile
import com.turtleteam.api.api.model.UserProfileResponse
import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeRepositoryImpl(httpClient: HttpClient) : HomeRepository, BaseRepository(httpClient),
    KoinComponent {
    val settings: Settings by inject()

    override suspend fun getProfile(token: String): UserProfile {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/me",
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            ),
            parameters = mapOf(
                "auth_token" to token
            )
        )
        return Json.decodeFromString<UserProfileResponse>(response).result
    }

    override suspend fun getCards(token: String): List<Card> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/card/token",
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            ),
            parameters = mapOf(
                "auth_hash" to token
            )
        )
        return Json.decodeFromString<List<Card>>(response)
    }

    override suspend fun getPrivileges(card_token: String): ShortPrivileges {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/privileges/card",
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            ),
            parameters = mapOf(
                "card_hash" to card_token
            )
        )
        return Json.decodeFromString<ShortPrivileges>(response)
    }

    override suspend fun getAllPrivileges(): List<FullPrivileges> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/privileges/all",
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            ),
        )
        return Json.decodeFromString<List<FullPrivileges>>(response)
    }
}