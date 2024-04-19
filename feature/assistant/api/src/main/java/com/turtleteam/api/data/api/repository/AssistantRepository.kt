package com.turtleteam.api.data.api.repository

interface AssistantRepository {
    suspend fun sendMessage(message: String): String
}