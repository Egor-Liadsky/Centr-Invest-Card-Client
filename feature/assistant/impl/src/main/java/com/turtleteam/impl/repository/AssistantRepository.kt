package com.turtleteam.impl.repository

import com.turtleteam.api.Settings
import com.turtleteam.api.data.api.repository.AssistantRepository
import com.turtleteam.impl.model.Answer
import com.turtleteam.impl.presentation.viewModel.Message
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class AssistantRepositoryImpl(
    private val networkDataSource: AssistantNetworkDataSource,
    private val settings: Settings
): AssistantRepository {
    private val json = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
        isLenient = true
    }
    override suspend fun sendMessage(message: String): String {
        return json.decodeFromString<Answer>(networkDataSource.sendMessage(message)).answer
    }
    suspend fun getChat(): List<Message>?{
        if (settings.getChat() == null) return null
        return json.decodeFromString(settings.getChat()!!)
    }
    suspend fun setChat(chat: List<Message>) {
        settings.setChat(json.encodeToString(chat))
    }
}