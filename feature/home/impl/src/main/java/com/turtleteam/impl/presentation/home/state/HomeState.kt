package com.turtleteam.impl.presentation.home.state

import com.turtleteam.api.api.model.Card
import com.turtleteam.api.api.model.Category
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.model.Service
import com.turtleteam.api.data.api.model.User
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.models.ServiceHistory
import com.turtleteam.impl.presentation.home.viewModel.Services
import com.whatrushka.api.profile.models.ProfileData

data class HomeState(
    val cards: List<Card>? = null,
    val user: User? = null,
    val selectedPrivileges: Category? = null,
    val categories: List<Category>? = null,
    val serviceHistory: List<Service>? = null,
    val userProfile: ProfileData = ProfileData(),
    val privileges: List<FullPrivileges>? = null,
    val cardLoadingState: LoadingState = LoadingState.Loading,
    val categoriesLoadingState: LoadingState = LoadingState.Loading,
    val serviceLoadingState: LoadingState = LoadingState.Loading,
)