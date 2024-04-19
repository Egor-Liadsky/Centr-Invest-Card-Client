package com.lyadsky.centr_invest_card_client.components.welcome.auth

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.centr_invest_card_client.components.BaseComponent
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthState
import com.lyadsky.centr_invest_card_client.data.repository.welcome.WelcomeRepository
import com.lyadsky.centr_invest_card_client.utils.ErrorService
import com.lyadsky.centr_invest_card_client.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthComponentImpl(
    componentContext: ComponentContext,
    private val navigateToRegistration: () -> Unit,
    private val navigateToPincode: () -> Unit
): BaseComponent<AuthState>(componentContext, AuthState()), AuthComponent, KoinComponent {

    private val errorService: ErrorService by inject()
    private val welcomeRepository: WelcomeRepository by inject()

    override fun loginTextFieldValueChanged(value: String) {
        viewState = viewState.copy(loginTextFieldValue = value)
    }

    override fun passwordTextFieldValueChanged(value: String) {
        viewState = viewState.copy(passwordTextFieldValue = value)
    }

    override fun onRegistrationClick() {
        navigateToRegistration()
    }

    override fun showError(value: String) {
        scope.launch { errorService.showError(value) }
    }

    override fun onPincodeClick() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    val response = welcomeRepository.authUser(
                        viewState.loginTextFieldValue,
                        viewState.passwordTextFieldValue
                    )
                    println("TAGTAG   $response")
                    navigateToPincode()
                },
                failureBlock = { exception: Throwable ->
                    errorService.showError("Введите корректные данные")
                }
            )
        }
    }
}