package com.turtleteam.impl.navigation

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.turtleteam.api.navigation.EventNavigation
import com.turtleteam.impl.presentation.event.quiz.QuizScreen
import com.turtleteam.impl.presentation.event.screen.EventScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

const val quizRoute = "quiz/{id}"

class EventNavigationImpl : EventNavigation {

    override val baseRoute: String = "event"
    override fun quizRoute(id: Int): String = "quiz/$id"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = baseRoute) {
            val navigator =
                koinInject<EventNavigator>(parameters = { parametersOf(navController) })
            EventScreen(
                modifier,
                viewModel = koinViewModel(parameters = { parametersOf(navigator) })
            )
        }
        navGraphBuilder.composable(
            route = quizRoute,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val arg = it.arguments?.getInt("id")
            val navigator =
                koinInject<EventNavigator>(parameters = { parametersOf(navController) })
            QuizScreen(
                modifier,
                viewModel = koinViewModel(parameters = { parametersOf(navigator,arg) })
            )
        }
    }
}