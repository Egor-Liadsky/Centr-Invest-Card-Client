package com.turtleteam.impl.repository

import com.turtleteam.api.navigation.EventRepository
import com.turtleteam.api.navigation.model.Quiz

class EventRepositoryImpl(
    private val dataSource: EventDataSource
): EventRepository {
    override suspend fun getQuizList(): List<Quiz> {
        return dataSource.getQuiz().toList()
    }
}