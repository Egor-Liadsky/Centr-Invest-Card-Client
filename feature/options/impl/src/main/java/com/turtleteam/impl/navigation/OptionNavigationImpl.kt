package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.navigation.OptionNavigation
import com.turtleteam.impl.presentation.option.screen.OptionsScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class OptionNavigationImpl : OptionNavigation {

    override val baseRoute: String = "option"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val navigator =
                koinInject<OptionNavigator>(parameters = { parametersOf(navController) })
            OptionsScreen(
                modifier,
                viewModel = koinViewModel(parameters = { parametersOf(navigator) })
            )
        }
    }
}