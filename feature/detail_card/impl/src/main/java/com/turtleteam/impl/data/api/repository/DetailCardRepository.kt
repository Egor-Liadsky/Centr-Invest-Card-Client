package com.turtleteam.impl.data.api.repository

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.data.api.repository.DetailCardRepository
import com.turtleteam.api.data.model.Service
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DetailCardRepositoryImpl(httpClient: HttpClient) : DetailCardRepository,
    BaseRepository(httpClient) {

    override suspend fun getServiceHistory(token: String): List<Service> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "user/cash/history",
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
            ),
            parameters = mapOf(
                "user_token" to token
            )
        )
        return Json.decodeFromString(response)
    }
}