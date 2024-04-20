package com.turtleteam.api.data.api.repository

import com.turtleteam.api.data.model.Service

interface DetailCardRepository {

    suspend fun getServiceHistory(token: String): List<Service>
}