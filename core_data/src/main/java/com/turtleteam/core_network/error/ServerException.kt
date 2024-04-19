package com.turtleteam.core_network.error

data class ServerException(
    val errorCode: Int,
    val errorMessage: String
) : Exception(errorMessage)
