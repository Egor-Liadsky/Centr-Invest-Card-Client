package com.turtleteam.api.api.repository

import com.turtleteam.api.api.model.Card

interface HomeRepository {

    suspend fun getCards(token: String): List<Card>?
}