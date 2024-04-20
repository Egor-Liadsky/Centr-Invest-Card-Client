package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.OptionNavigation

class OptionNavigator (
    private val optionNavigation: OptionNavigation,
    private val navController: NavController
) {
    private val baseRoute = optionNavigation.baseRoute
}