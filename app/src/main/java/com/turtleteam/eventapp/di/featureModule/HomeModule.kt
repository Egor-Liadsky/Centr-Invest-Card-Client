package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.api.data.api.service.AccountService
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.api.navigation.HomeNavigation
import com.turtleteam.impl.data.api.repository.AccountRepositoryImpl
import com.turtleteam.impl.data.api.repository.HomeRepositoryImpl
import com.turtleteam.impl.data.api.service.AccountServiceImpl
import com.turtleteam.impl.navigation.AccountNavigationImpl
import com.turtleteam.impl.navigation.HomeNavigationImpl
import com.turtleteam.impl.navigation.HomeNavigator
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<HomeRepository> { HomeRepositoryImpl(get()) }

    single<HomeNavigation> { HomeNavigationImpl() }
    factory { navController ->
        HomeNavigator(get(), get(), get(), get(), get(), navController.get())
    }
    viewModel { navigator ->
        HomeViewModel(navigator.get(), get(), get(), get(), get())
    }
}