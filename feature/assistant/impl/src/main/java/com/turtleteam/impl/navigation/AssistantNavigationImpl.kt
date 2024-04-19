package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.AssistantNavigation
import com.turtleteam.impl.presentation.screen.AssistantScreen
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class AssistantNavigationImpl : AssistantNavigation {

    override val baseRoute: String = "assistant"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(baseRoute) {
            val navigator =
                koinInject<AssistantNavigator>(parameters = { parametersOf(navController) })
            AssistantScreen(
                modifier,
                viewModel = koinInject(parameters = { parametersOf(navigator) }))
        }
    }
}