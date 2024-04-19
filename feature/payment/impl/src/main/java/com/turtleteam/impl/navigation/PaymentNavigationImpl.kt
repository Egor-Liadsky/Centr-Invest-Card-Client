package com.turtleteam.impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.turtleteam.api.PaymentNavigation
import com.turtleteam.impl.presentation.payment.screen.PaymentScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

class PaymentNavigationImpl : PaymentNavigation {

    override val baseRoute: String = "payment"

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = "$baseRoute/{payment_type}") { args ->
            val paymentType = args.arguments?.getString("payment_type")
            val navigator =
                koinInject<PaymentNavigator>(parameters = { parametersOf(navController) })
            PaymentScreen(
                paymentType = paymentType.toString(),
                viewModel = koinViewModel(parameters = { parametersOf(navigator) })
            )
        }
    }
}