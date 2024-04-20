package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.api.repository.HomeRepository
import com.turtleteam.api.data.api.repository.DetailCardRepository
import com.turtleteam.api.navigation.DetailCardNavigation
import com.turtleteam.impl.data.api.repository.DetailCardRepositoryImpl
import com.turtleteam.impl.data.api.repository.HomeRepositoryImpl
import com.turtleteam.impl.navigation.DetailCardNavigationImpl
import com.turtleteam.impl.navigation.DetailCardNavigator
import com.turtleteam.impl.presentation.detail_card.viewModel.DetailCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailCardModule = module {
    single<DetailCardNavigation> { DetailCardNavigationImpl() }
    single<DetailCardRepository> { DetailCardRepositoryImpl(get()) }
    factory { navController ->
        DetailCardNavigator(navController.get())
    }

    viewModel { params ->
        DetailCardViewModel(params.get(), get())
    }
}