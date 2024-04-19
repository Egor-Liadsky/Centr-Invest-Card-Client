package com.turtleteam.api.navigation

import com.turtleteam.core_navigation.NavigationApi

interface EventNavigation : NavigationApi {

    val baseRoute: String
    fun quizRoute(id: Int): String
}