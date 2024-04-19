package com.turtleteam.api.navigation

import com.turtleteam.api.navigation.model.Quiz

interface EventRepository {

    suspend fun getQuizList(): List<Quiz>
}