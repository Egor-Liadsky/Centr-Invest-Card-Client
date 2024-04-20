package com.turtleteam.api.api.repository

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.model.ShortPrivileges

interface HomeRepository {

    suspend fun getCards(token: String): List<Card>?

    suspend fun getPrivileges(card_token: String): ShortPrivileges

    suspend fun getAllPrivileges(): List<FullPrivileges>
}