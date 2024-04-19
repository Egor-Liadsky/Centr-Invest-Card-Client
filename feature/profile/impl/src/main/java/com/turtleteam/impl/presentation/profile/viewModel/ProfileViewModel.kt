package com.turtleteam.impl.presentation.profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.data.api.model.User
import com.turtleteam.impl.navigation.ProfileNavigator
import com.turtleteam.impl.presentation.profile.state.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ProfileViewModel(
    private val navigator: ProfileNavigator,
    settings: Settings
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = Json.decodeFromString<User>(settings.getUser() ?: "")
            _state.update { it.copy(user = user) }
        }
    }
}