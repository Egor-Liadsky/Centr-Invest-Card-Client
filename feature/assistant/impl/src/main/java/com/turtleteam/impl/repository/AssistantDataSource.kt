package com.turtleteam.impl.repository

import com.turtleteam.core_network.BaseRepository
import com.turtleteam.core_network.URL
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class AssistantNetworkDataSource(
    client: HttpClient
) : BaseRepository(client) {

    suspend fun sendMessage(message: String): String {
        return executeCall(
            url = URL.TURTLE_BOT,
            path = "api/assistant/quest",
            headers = mapOf(
                "Content-Type" to "application/json"
            ),
            body = """
                {
                    "text": "$message"
                }
            """.trimIndent(),
            type = HttpMethod.Post
        )
    }
}