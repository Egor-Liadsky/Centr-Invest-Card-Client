package com.turtleteam.api.data.api.repository

import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.data.api.model.UserDTOReceive

interface AccountRepository {

    suspend fun registerUser(user: UserDTOReceive): User
    suspend fun authUser(login: String, password: String): User
}