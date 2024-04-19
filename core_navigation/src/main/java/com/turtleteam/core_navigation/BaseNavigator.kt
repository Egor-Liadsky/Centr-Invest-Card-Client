package com.turtleteam.core_navigation

import androidx.navigation.NavController

abstract class BaseNavigator(private val navController: NavController) {

    fun onBackButtonClick() {
        navController.popBackStack()
    }
}