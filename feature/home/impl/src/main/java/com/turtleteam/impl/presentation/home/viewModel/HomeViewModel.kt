package com.turtleteam.impl.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.api.data.api.model.User
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
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
                        _state.update {
                            it.copy(
                                cards = cards.await(), user = user.await(),
                                cardLoadingState = LoadingState.Success
                            )
                        }
                    },
                    failureBlock = {
                        errorService.showError("Ошибка с соединением ")
                        _state.update { it.copy(cardLoadingState = LoadingState.Error("")) }
                    }
                )
            }
        }
    }

    fun navigateToDetailCard(cardId: String) {
        navigator.navigateToDetailCard(cardId)
    }

    fun navigateToProfile() {
        navigator.navigateToProfile()
    }

    fun getPrivileges() {
        viewModelScope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    val user_privilege =
                        state.value.cards?.get(0)?.key?.let { homeRepository.getPrivileges(it) }

                    val all_privileges = homeRepository.getAllPrivileges()

                    val arr = mutableListOf<FullPrivileges>()

                    all_privileges.forEach { all ->
                        user_privilege?.result?.forEach { user ->
                            if (user == all.privileges_prefix) {
                                arr.add(all)
                            }
                        }
                    }

                    _state.update {
                        it.copy(
                            privileges = arr,
                            privilegesLoadingState = LoadingState.Success
                        )
                    }
                },
                failureBlock = {
                    errorService.showError("Ошибка с соединением ")
                    _state.update { it.copy(privilegesLoadingState = LoadingState.Error("")) }
                }
            )
        }
    }

    fun onSelectServiceClick(service: Services) {
        _state.update { it.copy(selectedService = service) }
    }
}

enum class Services {
    Medical,
    Privileges
}