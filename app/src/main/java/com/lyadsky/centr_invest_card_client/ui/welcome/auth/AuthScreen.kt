package com.lyadsky.centr_invest_card_client.ui.welcome.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.centr_invest_card_client.R
import com.lyadsky.centr_invest_card_client.components.welcome.auth.AuthComponent
import com.lyadsky.centr_invest_card_client.ui.views.textField.CustomTextField
import com.lyadsky.centr_invest_card_client.utils.LoadingState
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun AuthScreen(
    component: AuthComponent,
) {

    val state = component.viewStates.subscribeAsState()
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF049C6B))
            .clipToBounds(),
        toolbarModifier = Modifier.background(Color(0xFF049C6B)),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF049C6B))
                    .pin(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 30.dp, start = 16.dp),
                    text = "Авторизация",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

        }) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    text = "Для входа в приложение введите логин и пароль",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),

                        )
                )
            }
            item {
                Column(Modifier.padding(horizontal = 16.dp)) {
                    CustomTextField(
                        value = state.value.loginTextFieldValue,
                        onValueChange = { component.loginTextFieldValueChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = "Логин",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    CustomTextField(
                        value = state.value.passwordTextFieldValue,
                        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                        trailingIcon = {
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = { passwordHidden = !passwordHidden }) {
                                val iconPainter =
                                    painterResource(id = if (passwordHidden) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                                Icon(painter = iconPainter, contentDescription = null)
                            }
                        },
                        onValueChange = { component.passwordTextFieldValueChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        }),
                        placeholder = "Пароль",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    TextButton(onClick = { component.onRegistrationClick() }) {
                        Text(text = "Зарегистрироваться")
                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .fillMaxWidth()
                            .height(51.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF049C6B),
                            contentColor = Color.White
                        ),
                        onClick = {
                            focusManager.clearFocus()
                            component.onPincodeClick()
//                            if (state.value.loginTextFieldValue == "" || state.value.passwordTextFieldValue == "") {
//                                isError = true
//                                component.showError("Заполните все поля")
//                            } else {
////                                viewModel.onAuthClick(
////                                    state.value.loginText,
////                                    state.value.passwordText
////                                )
//                            }
                        }) {
                        if (state.value.authLoadingState == LoadingState.Loading) {
                            CircularProgressIndicator(
                                Modifier.size(24.dp),
                                color = Color.Black
                            )
                        } else {
                            Text(
                                text = "Войти",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFFFFFFF),

                                    textAlign = TextAlign.Center,
                                    letterSpacing = 0.1.sp,
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
