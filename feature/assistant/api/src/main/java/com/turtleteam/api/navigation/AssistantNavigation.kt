package com.turtleteam.api.navigation

import com.turtleteam.core_navigation.NavigationApi

interface AssistantNavigation: NavigationApi {

    val baseRoute: String
}