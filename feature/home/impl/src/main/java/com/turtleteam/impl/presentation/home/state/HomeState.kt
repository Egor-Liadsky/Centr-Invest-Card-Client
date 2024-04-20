package com.turtleteam.impl.presentation.home.state

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.data.api.model.User
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.impl.presentation.home.viewModel.Services

data class HomeState(
    val cards: List<Card>? = null,
    val user: User? = null,
    val privileges: List<FullPrivileges>? = null,
    val cardLoadingState: LoadingState = LoadingState.Loading,
    val privilegesLoadingState: LoadingState = LoadingState.Loading,
    val selectedService: Services = Services.Privileges
)