package com.turtleteam.impl.presentation.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.api.data.api.model.User
import com.turtleteam.api.model.PaymentType
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.HomeNavigator
import com.turtleteam.impl.presentation.home.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HomeViewModel(
    private val navigator: HomeNavigator,
    private val homeRepository: HomeRepository,
    private val settings: Settings,
    private val errorService: ErrorService
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (settings.getToken() != null) {
                exceptionHandleable(
                    executionBlock = {
                        val user = async {
                            kotlin.runCatching {
                                val userStr = settings.getUser()
                                Json.decodeFromString<User>(userStr!!)
                            }.getOrNull()
                        }

                        val cards = async { homeRepository.getCards(settings.getToken() ?: "") }
                        _state.update { it.copy(cards = cards.await(), user =user.await()) }
                    },
                    failureBlock = {
                        errorService.showError(settings.getToken().toString())
                        Log.e("TAGTAG", settings.getToken().toString())
                    }
                )
            }
        }
    }

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }

    fun navigateToAssistant() {
        navigator.navigateToAssistant()
    }

    fun navigateToPayment(paymentType: PaymentType) {
        navigator.navigateToPayment(paymentType)
    }

    fun navigateToDetailCard(cardId: String) {
        navigator.navigateToDetailCard(cardId)
    }
}