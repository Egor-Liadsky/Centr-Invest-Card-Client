package com.turtleteam.core_navigation.error

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.turtleteam.core_navigation.NavigationApi

fun NavGraphBuilder.register(
    featureApi: NavigationApi,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureApi.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}