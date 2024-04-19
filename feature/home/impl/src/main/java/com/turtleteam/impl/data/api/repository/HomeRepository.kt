package com.turtleteam.impl.data.api.repository

import com.turtleteam.api.api.model.CardShort
import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.core_network.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HomeRepositoryImpl(httpClient: HttpClient) : HomeRepository,
    BaseRepository(httpClient) {

    override suspend fun getCards(token: String): List<CardShort> {
//        val response = executeCall(
//            type = HttpMethod.Get,
//            path = "children_account/cards",
//            headers = mapOf(
//                "Authorization" to "Bearer $token",
//                "Content-Type" to "application/json",
//                "Accept" to "application/json",
//            )
//        )
//        return Json.decodeFromString<List<CardShort>>(response)
        return listOf(
            CardShort(1,"22/22","1","2222 2222 2222 2222", "22/21","22/23")
        )
    }
}