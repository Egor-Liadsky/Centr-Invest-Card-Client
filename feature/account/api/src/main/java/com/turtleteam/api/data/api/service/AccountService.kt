package com.turtleteam.api.data.api.service

import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.data.api.model.UserDTOReceive

interface AccountService {

    suspend fun registerUser(user: UserDTOReceive): User
    suspend fun authUser(login: String, password: String): User
}