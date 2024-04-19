package com.lyadsky.centr_invest_card_client.exceptions

data class ServerException(
    val errorCode: Int,
    val errorMessage: String
): Exception(errorMessage)