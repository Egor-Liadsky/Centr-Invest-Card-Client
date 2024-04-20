package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.ProfileNavigation
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.AssistantNavigation
import com.turtleteam.api.navigation.DetailCardNavigation
import com.turtleteam.api.navigation.HomeNavigation

class HomeNavigator (
    homeNavigation: HomeNavigation,
    accountNavigation: AccountNavigation,
    assistantNavigation: AssistantNavigation,
    profileNavigation: ProfileNavigation,
    detailCardNavigation: DetailCardNavigation,
    private val navController: NavController
) {
    private val baseRoute = homeNavigation.baseRoute
    private val accountRoute = accountNavigation.baseRoute
    private val assistantRoute = assistantNavigation.baseRoute
    private val profileRoute = profileNavigation.baseRoute
    private val detailCardRoute = detailCardNavigation.baseRoute

    fun navigateToWelcome() {
        navController.navigate(accountRoute)
    }

    fun navigateToAssistant() {
       navController.navigate(assistantRoute)
    }

    fun navigateToProfile() {
        navController.navigate(profileRoute)
    }

    fun navigateToDetailCard(cardId: String) {
        navController.navigate(route ="$detailCardRoute/$cardId")
    }
}