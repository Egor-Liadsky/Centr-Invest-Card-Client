package com.turtleteam.impl.presentation.option.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.api.Settings
import com.turtleteam.api.data.api.model.User
import com.turtleteam.impl.navigation.OptionNavigator
import com.turtleteam.impl.presentation.option.state.OptionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class OptionsViewModel(
    private val navigator: OptionNavigator,
    settings: Settings
) : ViewModel() {

    private val _state = MutableStateFlow(OptionsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = Json.decodeFromString<User>(settings.getUser() ?: "")
            _state.update { it.copy(user = user) }
        }
    }
}