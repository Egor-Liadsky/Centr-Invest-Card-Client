package com.turtleteam.impl.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.impl.presentation.auth.screen.AuthScreen
import com.turtleteam.impl.presentation.pincode.PincodeScreen
import com.turtleteam.impl.presentation.register.screen.RegisterScreen
import com.turtleteam.impl.presentation.welcome.screen.WelcomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

internal const val accountGraph = "account"
internal const val welcomeRoute = "$accountGraph/welcome"
internal const val authRoute = "$accountGraph/auth"
internal const val registerRoute = "$accountGraph/register"
internal const val PINcodeRoute = "pincode"

class AccountNavigationImpl : AccountNavigation {

    override val baseRoute: String = accountGraph
    override val pincodeRoute: String = PINcodeRoute

    private val animDuration = 500

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(pincodeRoute){
            val navigator =
                koinInject<AccountNavigator>(parameters = { parametersOf(navController) })
            PincodeScreen(viewModel = koinViewModel(parameters = { parametersOf(navigator) }))
        }
        navGraphBuilder.navigation(startDestination = welcomeRoute, route = baseRoute) {
            composable(
                route = welcomeRoute,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                }
            ) {
                val navigator =
                    koinInject<AccountNavigator>(parameters = { parametersOf(navController) })
                WelcomeScreen(
                    viewModel = koinViewModel(parameters = { parametersOf(navigator) })
                )
            }
            composable(
                route = authRoute,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                }
            ) {
                val navigator =
                    koinInject<AccountNavigator>(parameters = { parametersOf(navController) })
                AuthScreen(
                    viewModel = koinViewModel(parameters = { parametersOf(navigator) })
                )
            }
            composable(route = registerRoute,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(animDuration)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(animDuration)
                    )
                }
            ) {
                val navigator =
                    koinInject<AccountNavigator>(parameters = { parametersOf(navController) })
                RegisterScreen(
                    viewModel = koinViewModel(parameters = { parametersOf(navigator) })
                )
            }
        }
    }
}