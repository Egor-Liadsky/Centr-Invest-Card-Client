package com.turtleteam.impl.presentation.option.state

import com.turtleteam.api.data.api.model.User
import com.whatrushka.api.profile.models.ProfileData

data class OptionsState(
    val user: User? = null,
    val userData: ProfileData = ProfileData(),
    val pinCode: String? = null
)