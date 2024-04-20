package com.turtleteam.impl.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turtleteam.api.navigation.OptionNavigation
import com.turtleteam.impl.presentation.additional.aboutApp.AboutAppScreen
import com.turtleteam.impl.presentation.additional.faq.FaqScreen
import com.turtleteam.impl.presentation.additional.feedback.FeedbackScreen
import com.turtleteam.impl.presentation.option.screen.OptionsScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class OptionNavigationImpl : OptionNavigation {

    override val baseRoute: String = "option"

    private val animDuration = 500

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

        navGraphBuilder.composable(route = "$baseRoute/aboutApp") {
            val navigator =
                koinInject<OptionNavigator>(parameters = { parametersOf(navController) })
            AboutAppScreen(viewModel = koinViewModel(parameters = { parametersOf(navigator) }))
        }

        navGraphBuilder.composable(route = "$baseRoute/feedback") {
            val navigator =
                koinInject<OptionNavigator>(parameters = { parametersOf(navController) })
            FeedbackScreen(viewModel = koinViewModel(parameters = { parametersOf(navigator) }))
        }

        navGraphBuilder.composable(route = "$baseRoute/faq") {
            val navigator =
                koinInject<OptionNavigator>(parameters = { parametersOf(navController) })
            FaqScreen(viewModel = koinViewModel(parameters = { parametersOf(navigator) }))
        }
    }
}