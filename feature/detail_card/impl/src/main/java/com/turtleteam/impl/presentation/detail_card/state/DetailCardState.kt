package com.turtleteam.impl.presentation.detail_card.state

import com.turtleteam.api.data.api.model.User
import com.whatrushka.api.profile.models.ProfileData
import com.turtleteam.api.data.model.Service
import com.turtleteam.core_navigation.state.LoadingState

data class DetailCardState(
    val isDetailsShown: Boolean = false,
    val serviceHistory: List<Service>? = null,
    val limitBegin: Int? = null,
    val limitEnd: Int? = null,
    val user: User? = null,
    val cardDate: String ="08/28",
    val cardCvc: String = "412",
    val userData: ProfileData = ProfileData(),
    val isRefreshing: Boolean = false,
    val serviceHistoryLoadingState: LoadingState = LoadingState.Loading
)