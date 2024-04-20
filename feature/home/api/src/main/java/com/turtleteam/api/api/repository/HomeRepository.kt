package com.turtleteam.api.api.repository

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.model.ShortPrivileges
import com.turtleteam.api.api.model.UserProfile
import com.whatrushka.api.profile.models.ProfileData

interface HomeRepository {

    suspend fun getProfile(token: String): UserProfile

    suspend fun getCards(token: String): List<Card>?

    suspend fun getPrivileges(card_token: String): ShortPrivileges

    suspend fun getAllPrivileges(): List<FullPrivileges>
}