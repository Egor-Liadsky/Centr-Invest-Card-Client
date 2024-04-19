package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.navigation.ProfileNavigation
import com.turtleteam.impl.navigation.ProfileNavigationImpl
import com.turtleteam.impl.navigation.ProfileNavigator
import com.turtleteam.impl.presentation.profile.viewModel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    single<ProfileNavigation> { ProfileNavigationImpl() }
    factory { navController ->
        ProfileNavigator(get(), navController.get())
    }
    viewModel { navigator ->
        ProfileViewModel(navigator.get(), get())
    }
}