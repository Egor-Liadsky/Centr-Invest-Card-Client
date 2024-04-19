package com.lyadsky.centr_invest_card_client.exceptions

data class SocketException(
    private val _message: String? = null
): Exception(_message)