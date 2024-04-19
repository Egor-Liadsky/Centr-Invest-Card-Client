package com.turtleteam.impl.presentation.payment.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.api.model.PaymentType
import com.turtleteam.impl.navigation.PaymentNavigator
import com.turtleteam.impl.presentation.payment.state.PaymentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PaymentViewModel(
    private val navigator: PaymentNavigator,
): ViewModel() {

    private val _state = MutableStateFlow(PaymentState())
    val state = _state.asStateFlow()

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }

    fun phoneVariantTextChanged(text: String) {
        _state.update { it.copy(phoneVariantText = text) }
    }

    fun sumTextChanged(sum: String) {
        _state.update { it.copy(sumText = sum) }
    }

    fun commentTextChanged(text: String) {
        _state.update { it.copy(commentText = text) }
    }

    fun onClickSelectBank(text: String) {
        _state.update { it.copy(selectBank = text) }
    }

    fun onClickSelectCategory(text: String) {
        _state.update { it.copy(selectCategory = text) }
    }
    fun onTransactionClick(){
        navigator.onBackButtonClick()
    }
}