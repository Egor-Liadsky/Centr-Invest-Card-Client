package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.HomeNavigation
import com.turtleteam.core_navigation.BaseNavigator

class AccountNavigator(
    private val homeNavigation: HomeNavigation,
    private val navController: NavController
) : BaseNavigator(navController) {

    fun navigateToAuth() {
        navController.navigate(authRoute) {
            launchSingleTop = true
        }
    }

    fun navigateToRegister() {
        navController.navigate(registerRoute) {
            launchSingleTop = true
        }
    }
    fun navigateToPincode() {
        navController.navigate(PINcodeRoute) {
            popUpTo(0){
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    fun navigateToHome() {
        navController.navigate(homeNavigation.baseRoute) {
            popUpTo(0){
                inclusive = true
            }
            launchSingleTop = true
        }
    }
}