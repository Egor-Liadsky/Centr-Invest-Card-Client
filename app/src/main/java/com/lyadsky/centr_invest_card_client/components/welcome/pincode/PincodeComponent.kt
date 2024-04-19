package com.lyadsky.centr_invest_card_client.components.welcome.pincode

import com.arkivanov.decompose.value.Value

interface PincodeComponent {

    val viewStates: Value<PincodeState>

    fun onPincodeTextChanged(char: String)

    fun onBackspaceClick()

    fun onDoneClick()
}