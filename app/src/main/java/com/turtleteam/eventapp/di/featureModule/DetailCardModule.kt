package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.navigation.DetailCardNavigation
import com.turtleteam.impl.navigation.DetailCardNavigationImpl
import com.turtleteam.impl.navigation.DetailCardNavigator
import com.turtleteam.impl.presentation.detail_card.viewModel.DetailCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailCardModule = module {
    single<DetailCardNavigation> { DetailCardNavigationImpl() }

    factory { navController ->
        DetailCardNavigator(navController.get())
    }

    viewModel { params ->
        DetailCardViewModel(params.get(), get())
    }
}