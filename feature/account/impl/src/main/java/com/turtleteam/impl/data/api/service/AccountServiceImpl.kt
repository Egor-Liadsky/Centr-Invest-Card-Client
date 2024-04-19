package com.turtleteam.impl.data.api.service

import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.api.data.api.service.AccountService
import com.turtleteam.impl.extension.hashString

class AccountServiceImpl(private val repository: AccountRepository) : AccountService {

    override suspend fun registerUser(user: UserDTOReceive): User {
        return repository.registerUser(user)
    }

    override suspend fun authUser(login: String, password: String): User {
        return repository.authUser(login, password)
    }
}
