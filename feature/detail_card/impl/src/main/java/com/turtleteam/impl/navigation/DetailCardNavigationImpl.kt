package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.turtleteam.api.navigation.DetailCardNavigation
import com.turtleteam.impl.presentation.detail_card.screen.DetailCardScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class DetailCardNavigationImpl : DetailCardNavigation {

    override val baseRoute: String = "detail_card"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = "$baseRoute/{id}") { args ->
            val id = args.arguments?.getString("id")
            val navigator = koinInject<DetailCardNavigator>(parameters = { parametersOf(navController) })
            DetailCardScreen(cardId = id.toString(), viewModel = koinViewModel(parameters = { parametersOf(navigator) }))
        }
    }
}