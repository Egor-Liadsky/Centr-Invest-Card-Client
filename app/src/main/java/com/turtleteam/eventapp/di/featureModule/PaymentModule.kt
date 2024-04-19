package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.PaymentNavigation
import com.turtleteam.impl.navigation.PaymentNavigationImpl
import com.turtleteam.impl.navigation.PaymentNavigator
import com.turtleteam.impl.presentation.payment.viewModel.PaymentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentModule = module {
    single<PaymentNavigation> { PaymentNavigationImpl() }

    factory { navController ->
        PaymentNavigator(navController.get())
    }

    viewModel { params ->
        PaymentViewModel(params.get())
    }
}