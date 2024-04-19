package com.lyadsky.centr_invest_card_client.components.welcome.registration

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.centr_invest_card_client.components.BaseComponent
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthState
import com.lyadsky.centr_invest_card_client.data.model.UserDTOReceive
import com.lyadsky.centr_invest_card_client.data.repository.welcome.WelcomeRepository
import com.lyadsky.centr_invest_card_client.utils.ErrorService
import com.lyadsky.centr_invest_card_client.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegistrationComponentImpl(
    componentContext: ComponentContext,
    private val backButtonClick: () -> Unit,
    private val navigateToPincode: () -> Unit
) : BaseComponent<RegistrationState>(componentContext, RegistrationState()), RegistrationComponent,
    KoinComponent {

    private val errorService: ErrorService by inject()
    private val welcomeRepository: WelcomeRepository by inject()

    override fun firstNameTextFieldValueChanged(value: String) {
        viewState = viewState.copy(firstNameTextFieldValue = value)
    }

    override fun lastNameTextFieldValueChanged(value: String) {
        viewState = viewState.copy(lastNameTextFieldValue = value)
    }

    override fun surnameTextFieldValueChanged(value: String) {
        viewState = viewState.copy(surnameTextFieldValue = value)
    }

    override fun loginTextFieldValueChanged(value: String) {
        viewState = viewState.copy(loginTextFieldValue = value)
    }

    override fun passwordTextFieldValueChanged(value: String) {
        viewState = viewState.copy(passwordTextFieldValue = value)
    }

    override fun showError(value: String) {
        scope.launch { errorService.showError(value) }
    }

    override fun onBackButtonClick() {
        backButtonClick()
    }

    override fun onPincodeClick() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    val response = welcomeRepository.registerUser(
                        UserDTOReceive(
                            username = viewState.loginTextFieldValue,
                            password = viewState.passwordTextFieldValue,
                            name = viewState.firstNameTextFieldValue,
                            family = viewState.lastNameTextFieldValue,
                            two_name = viewState.surnameTextFieldValue,
                        )
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