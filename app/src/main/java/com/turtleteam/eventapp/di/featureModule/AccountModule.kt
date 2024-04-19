package com.turtleteam.eventapp.di.featureModule

import com.turtleteam.api.data.api.repository.AccountRepository
import com.turtleteam.api.data.api.service.AccountService
import com.turtleteam.api.navigation.AccountNavigation
import com.turtleteam.impl.data.api.repository.AccountRepositoryImpl
import com.turtleteam.impl.data.api.service.AccountServiceImpl
import com.turtleteam.impl.navigation.AccountNavigationImpl
import com.turtleteam.impl.navigation.AccountNavigator
import com.turtleteam.impl.presentation.auth.viewModel.AuthViewModel
import com.turtleteam.impl.presentation.pincode.viewmodel.PincodeViewModel
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel
import com.turtleteam.impl.presentation.welcome.viewModel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    single<AccountNavigation> { AccountNavigationImpl() }
    single<AccountRepository> { AccountRepositoryImpl(get()) }
    single<AccountService> { AccountServiceImpl(get()) }

    factory { navController ->
        AccountNavigator(get(),navController.get())
    }

    viewModel { params ->
        PincodeViewModel(get(),get() ,params.get())
    }

    viewModel { params ->
        AuthViewModel(params.get(), get(), get(), get())
    }
    viewModel { params ->
        RegisterViewModel(params.get(), get(), get())
    }
    viewModel { params ->
        WelcomeViewModel(params.get())
    }
}