package com.turtleteam.eventapp.navigation

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turtleteam.api.PaymentNavigation
import com.turtleteam.api.Settings
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.AssistantNavigation
import com.turtleteam.api.navigation.DetailCardNavigation
import com.turtleteam.api.navigation.EventNavigation
import com.turtleteam.api.navigation.HomeNavigation
import com.turtleteam.api.navigation.ProfileNavigation
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.error.register
import com.turtleteam.core_view.BottomNavigationBar
import com.turtleteam.core_view.NavigationItem
import com.turtleteam.core_view.R
import com.turtleteam.eventapp.di.featureModule.paymentModule
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.compose.koinInject


@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    errorService: ErrorService = koinInject(),
    settings: Settings = koinInject()
) {

    val homeFeature: HomeNavigation = koinInject()
    val eventFeature: EventNavigation = koinInject()
    val profileFeature: ProfileNavigation = koinInject()
    val accountFeature: AccountNavigation = koinInject()
    val assistantFeature: AssistantNavigation = koinInject()
    val paymentFeature: PaymentNavigation = koinInject()
    val detailCardFeature: DetailCardNavigation = koinInject()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.substringBefore("/")
    val bottomBarColors =
        currentRoute.bottomNavBarColors(routes = listOf(eventFeature.baseRoute, "quiz"))
    LaunchedEffect(key1 = Unit, block = {
        navController.navigate(if (settings.getToken() == null) accountFeature.baseRoute else accountFeature.pincodeRoute) {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        }
    })

    val bottomNavigationItems = listOf(
        NavigationItem(
            route = homeFeature.baseRoute,
            label = R.string.bottom_navigation_view_home,
            icon = R.drawable.ic_home
        ),
        NavigationItem(
            route = assistantFeature.baseRoute,
            label = R.string.bottom_navigation_view_assistant,
            icon = R.drawable.ic_assistant
        ),
        NavigationItem(
            route = eventFeature.baseRoute,
            label = R.string.bottom_navigation_view_event,
            icon = R.drawable.ic_ticket
        ),
        NavigationItem(
            route = profileFeature.baseRoute,
            label = R.string.bottom_navigation_view_profile,
            icon = R.drawable.ic_profile
        )
    )

    LaunchedEffect(key1 = Unit) {
        errorService.state.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it, actionLabel = "Закрыть")
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                routes = bottomNavigationItems,
                currentRoute = currentRoute,
                onClick = {
                    navController.navigate(it) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                indicatorColor = bottomBarColors.second,
                containerColor = bottomBarColors.first
            )
        },
        snackbarHost = { snackbarHostStatet ->
            SnackbarHost(snackbarHostStatet, Modifier.zIndex(2f)) {
                Snackbar(modifier = Modifier.zIndex(2f), snackbarData = it)
            }
        }
    ) { paddingValues ->
        val bottomNavigationViewModifier =
            Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            navController = navController,
            startDestination = homeFeature.baseRoute
        ) {
            register(homeFeature, navController, bottomNavigationViewModifier)
            register(accountFeature, navController, bottomNavigationViewModifier)
            register(eventFeature, navController, bottomNavigationViewModifier)
            register(profileFeature, navController, bottomNavigationViewModifier)
            register(assistantFeature, navController, bottomNavigationViewModifier)
            register(paymentFeature, navController)
            register(detailCardFeature, navController)
        }
    }
}

@Composable
fun String?.bottomNavBarColors(routes: List<String>): Pair<Color, Color> {
    val green1 = Color(0xFFE8F5E9)
    val green2 = Color(0xFFA5D6A7)
    val red1 = Color(0xFFF5DEE0)
    val red2 = Color(0xFFF19AA2)

    val bottomBarColor = remember { Animatable(green1) }
    val indicatorColor = remember { Animatable(green2) }

    LaunchedEffect(key1 = this, block = {
        if (routes.contains(this@bottomNavBarColors)) {
            launch { indicatorColor.animateTo(targetValue = red2, tween(200)) }
            launch { bottomBarColor.animateTo(targetValue = red1, tween(200)) }
        } else {
            launch { indicatorColor.animateTo(targetValue = green2, tween(200)) }
            launch { bottomBarColor.animateTo(targetValue = green1, tween(200)) }
        }
    })
    return Pair(bottomBarColor.value, indicatorColor.value)
}