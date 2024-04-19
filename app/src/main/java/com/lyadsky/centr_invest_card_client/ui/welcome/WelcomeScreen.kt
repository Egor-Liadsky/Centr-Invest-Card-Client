package com.lyadsky.centr_invest_card_client.ui.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.lyadsky.centr_invest_card_client.components.welcome.WelcomeComponent
import com.lyadsky.centr_invest_card_client.ui.root.RootChildren
import com.lyadsky.centr_invest_card_client.ui.welcome.auth.AuthScreen
import com.lyadsky.centr_invest_card_client.ui.welcome.pincode.PincodeScreen
import com.lyadsky.centr_invest_card_client.ui.welcome.registration.RegistrationScreen

@Composable
fun WelcomeScreen(
    component: WelcomeComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is WelcomeComponent.Child.AuthChild -> AuthScreen(component = child.component)
            is WelcomeComponent.Child.PincodeChild -> PincodeScreen(component = child.component)
            is WelcomeComponent.Child.RegisterChild -> RegistrationScreen(component = child.component)
        }
    }
}