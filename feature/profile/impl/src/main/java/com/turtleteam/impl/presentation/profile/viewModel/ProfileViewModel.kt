package com.turtleteam.impl.presentation.profile.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.ProfileNavigator
import com.turtleteam.impl.presentation.profile.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val navigator: ProfileNavigator,
): ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

}