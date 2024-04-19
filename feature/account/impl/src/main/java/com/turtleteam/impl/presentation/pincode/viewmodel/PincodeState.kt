package com.turtleteam.impl.presentation.pincode.viewmodel

data class PincodeState(
    val pincode: String = "",
    val isError: Boolean = false,
    val saveBtnEnabled: Boolean = false,
    val pincodeIsNull: Boolean = false
)