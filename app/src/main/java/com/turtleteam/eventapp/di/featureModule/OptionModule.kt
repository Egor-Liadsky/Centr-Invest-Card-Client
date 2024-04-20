package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.navigation.OptionNavigation
import com.turtleteam.impl.navigation.OptionNavigationImpl
import com.turtleteam.impl.navigation.OptionNavigator
import com.turtleteam.impl.presentation.additional.aboutApp.AboutAppViewModel
import com.turtleteam.impl.presentation.additional.faq.FaqViewModel
import com.turtleteam.impl.presentation.additional.feedback.FeedbackViewModel
import com.turtleteam.impl.presentation.option.viewModel.OptionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val optionModule = module {
    single<OptionNavigation> { OptionNavigationImpl() }
    factory { navController ->
        OptionNavigator(get(), navController.get())
    }
    viewModel { navigator ->
        OptionsViewModel(navigator.get(), get())
    }

    viewModel { navigator ->
        AboutAppViewModel(navigator.get())
    }

    viewModel { navigator ->
        FeedbackViewModel(navigator.get())
    }

    viewModel { navigator ->
        FaqViewModel(navigator.get())
    }
}