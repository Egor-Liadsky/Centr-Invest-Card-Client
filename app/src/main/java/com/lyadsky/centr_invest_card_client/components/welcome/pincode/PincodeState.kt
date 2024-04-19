package com.lyadsky.centr_invest_card_client.components.welcome.pincode

data class PincodeState(
    val pincode: String = "",
    val isError: Boolean = false,
    val saveBtnEnabled: Boolean = false,
    val pincodeIsNull: Boolean = false
)