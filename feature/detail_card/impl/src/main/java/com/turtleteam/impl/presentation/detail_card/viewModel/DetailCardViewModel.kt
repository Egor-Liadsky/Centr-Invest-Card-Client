package com.turtleteam.impl.presentation.detail_card.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.data.api.model.User
import com.turtleteam.impl.navigation.DetailCardNavigator
import com.turtleteam.impl.presentation.detail_card.state.DetailCardState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DetailCardViewModel(
    private val navigator: DetailCardNavigator,
    private val settings: Settings
): ViewModel() {

    private val _state = MutableStateFlow(DetailCardState(
        limitBegin = 100,
        limitEnd = 100
    ))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = kotlin.runCatching {
                    val userStr = settings.getUser()
                    Json.decodeFromString<User>(userStr!!)
                }.getOrNull()
            _state.update { it.copy(user = user) }
        }
    }

    fun onBackButtonClick() {
        navigator.onBackButtonClick()
    }
    fun onShowRequisites(){
        _state.update { it.copy(isDetailsShown = true) }
    }
}