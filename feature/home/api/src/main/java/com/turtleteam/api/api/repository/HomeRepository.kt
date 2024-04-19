package com.turtleteam.api.api.repository

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.CardShort

interface HomeRepository {

    suspend fun getCards(token: String): List<CardShort>
}