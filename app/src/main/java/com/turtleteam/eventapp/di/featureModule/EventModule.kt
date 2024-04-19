package com.turtleteam.eventapp.di.featureModule

import android.util.Log
import androidx.navigation.NavController
import com.turtleteam.api.navigation.EventNavigation
import com.turtleteam.api.navigation.EventRepository
import com.turtleteam.impl.navigation.EventNavigationImpl
import com.turtleteam.impl.navigation.EventNavigator
import com.turtleteam.impl.presentation.event.quiz.QuizViewModel
import com.turtleteam.impl.presentation.event.viewModel.EventViewModel
import com.turtleteam.impl.repository.EventDataSource
import com.turtleteam.impl.repository.EventRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventModule = module {
    single<EventNavigation> { EventNavigationImpl() }
    factory { navController ->
        EventNavigator(get(), navController.get())
    }
    single { EventDataSource(get(),get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    viewModel { navigator ->
        EventViewModel(navigator.get(), get(),get())
    }
    viewModel { (navigator: EventNavigator, id: Int) ->
//        Log.e("TAGTAG", params[0])
        QuizViewModel(navigator, id, get())
    }
}