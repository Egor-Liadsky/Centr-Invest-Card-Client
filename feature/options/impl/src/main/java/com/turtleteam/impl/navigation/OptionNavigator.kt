package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.OptionNavigation
import com.turtleteam.core_navigation.BaseNavigator

class OptionNavigator (
    private val optionNavigation: OptionNavigation,
    private val navController: NavController
): BaseNavigator(navController) {
    private val baseRoute = optionNavigation.baseRoute

    fun navigateToAboutApp() {
        navController.navigate("$baseRoute/aboutApp") {
            launchSingleTop = true
        }
    }

    fun navigateToWelcome() {
        navController.navigate("account/welcome") {
            launchSingleTop = true
        }
    }

    fun navigateToFeedback() {
        navController.navigate("$baseRoute/feedback") {
            launchSingleTop = true
        }
    }

    fun navigateToFaq() {
        navController.navigate("$baseRoute/faq") {
            launchSingleTop = true
        }
    }
}