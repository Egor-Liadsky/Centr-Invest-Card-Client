package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.ProfileNavigation

class ProfileNavigator (
    private val profileNavigation: ProfileNavigation,
    private val navController: NavController
) {
    private val baseRoute = profileNavigation.baseRoute
}