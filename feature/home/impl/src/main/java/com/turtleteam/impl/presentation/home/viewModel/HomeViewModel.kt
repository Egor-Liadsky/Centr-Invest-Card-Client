package com.turtleteam.impl.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.api.model.Category
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.api.data.api.model.User
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_network.error.exceptionHandleable
import com.turtleteam.impl.navigation.HomeNavigator
import com.turtleteam.impl.presentation.home.state.HomeState
import com.whatrushka.api.profile.ProfileService
import com.whatrushka.api.profile.models.ProfileData
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
    private val errorService: ErrorService,
    private val profileService: ProfileService
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val token = settings.getToken()
            if (token != null) {
                exceptionHandleable(
                    executionBlock = {
                        val userProfile = homeRepository.getProfile(token)
                        profileService.saveUserProfile(
                            ProfileData(
                                name = userProfile.name,
                                family = userProfile.surname,
                                two_name = userProfile.patronymic,
                                username = userProfile.username,
                                cash = userProfile.cash
                            )
                        )

                        _state.update {
                            it.copy(userProfile = profileService.getUserProfile())
                        }

                        val user = async {
                            kotlin.runCatching {
                                val userStr = settings.getUser()
                                Json.decodeFromString<User>(userStr!!)
                            }.getOrNull()
                        }

                        val serviceHistory = homeRepository.getServiceHistory(token)

                        val categories = homeRepository.getCategories()

                        val cards = async { homeRepository.getCards(token) }
                        _state.update {
                            it.copy(
                                cards = cards.await(), user = user.await(),
                                categories = categories,
                                serviceHistory = serviceHistory,
                                cardLoadingState = LoadingState.Success,
                                categoriesLoadingState = LoadingState.Success,
                                serviceLoadingState = LoadingState.Success
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

    fun onPrivilegesClick(category: Category) {
        _state.update { it.copy(selectedPrivileges = category) }
    }

    fun getPrivileges() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = settings.getToken()
            if (token != null) {
                exceptionHandleable(
                    executionBlock = {

                        _state.update {
                            it.copy(
                                isRefreshing = true,
                                categoriesLoadingState = LoadingState.Loading,
                                serviceLoadingState = LoadingState.Loading
                            )
                        }
                        val serviceHistory = homeRepository.getServiceHistory(token)
                        val categories = homeRepository.getCategories()
                        _state.update {
                            it.copy(
                                isRefreshing = false,
                                categories = categories,
                                serviceHistory = serviceHistory,
                                categoriesLoadingState = LoadingState.Success,
                                serviceLoadingState = LoadingState.Success
                            )
                        }
                    },
                    failureBlock = {
                        errorService.showError("Ошибка с соединением ")
                        _state.update { it.copy(cardLoadingState = LoadingState.Error("")) }
                    },
                    completionBlock = {
                        _state.update {
                            it.copy(
                                isRefreshing = false,
                            )
                        }
                    }
                )
            }
        }
    }
}
