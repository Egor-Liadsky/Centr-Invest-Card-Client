package com.turtleteam.api.navigation

import com.turtleteam.core_navigation.NavigationApi

interface AccountNavigation : NavigationApi {

    val baseRoute: String
    val pincodeRoute: String
}