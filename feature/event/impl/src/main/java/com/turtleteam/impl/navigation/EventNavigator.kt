package com.turtleteam.impl.navigation

import androidx.navigation.NavController
import com.turtleteam.api.navigation.EventNavigation

class EventNavigator (
    private val eventNavigation: EventNavigation,
    private val navController: NavController
) {
    private val baseRoute = eventNavigation.baseRoute

    fun navigateTotQuiz(id: Int){
        navController.navigate(eventNavigation.quizRoute(id))
    }
    fun navigateBack(){
        navController.popBackStack()
    }
}