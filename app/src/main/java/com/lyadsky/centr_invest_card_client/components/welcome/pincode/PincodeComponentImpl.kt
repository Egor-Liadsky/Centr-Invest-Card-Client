package com.lyadsky.centr_invest_card_client.components.welcome.pincode

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.centr_invest_card_client.components.BaseComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal const val PINCODE_LENGTH = 4

class PincodeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToHome: () -> Unit
) : BaseComponent<PincodeState>(componentContext, PincodeState()), PincodeComponent {

    private var pincode: String? = null
    private var inputEnabled = true

    init {
        scope.launch(Dispatchers.IO) {
            pincode = null
            viewState = viewState.copy(pincodeIsNull = pincode == null)
        }
    }

    override fun onPincodeTextChanged(char: String) {
        val newStr = viewState.pincode + char
        if (inputEnabled) {
//            if (pincode == newStr)
//                navigator.navigateToHome()
                viewState = viewState.copy(
                    pincode = if (newStr.length <= PINCODE_LENGTH) newStr else viewState.pincode,
                    saveBtnEnabled = newStr.length >= PINCODE_LENGTH && this@PincodeComponentImpl.pincode == null
                )
        }
    }

    override fun onBackspaceClick() {
        if (inputEnabled)
            viewState = viewState.copy(pincode = viewState.pincode.dropLast(1), saveBtnEnabled = false)
    }

    override fun onDoneClick() {
        inputEnabled = false
//            settings.setPincode(_state.value.pincode)
                navigateToHome()

    }
}