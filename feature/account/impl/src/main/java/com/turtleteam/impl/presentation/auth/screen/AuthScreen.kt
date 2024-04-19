package com.turtleteam.impl.presentation.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.textfields.CustomTextField
import com.turtleteam.impl.presentation.auth.viewModel.AuthViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get

@Composable
fun AuthScreen(
    viewModel: AuthViewModel,
    errorService: ErrorService = get()
) {

    val state = viewModel.state.collectAsState()
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
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { viewModel.onBackButtonClick()}) {
                        Icon(
                            tint = Color.White,
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = ""
                        )
                    }
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
                Image(
                    painter = painterResource(id = R.drawable.ic_turtle_3),
                    contentDescription = "",
                    modifier = Modifier
                        .height(90.dp)
                        .offset(y = 8.dp)
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
                    modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
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
                        value = state.value.loginText,
                        icon = null,
                        singleLine = true,
                        onValueChange = { viewModel.onLoginTextChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        placeholder ="Введите логин или почту",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    CustomTextField(
                        value = state.value.passwordText,
                        icon = null,
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
                        singleLine = true,
                        onValueChange = { viewModel.onPasswordTextChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        }),
                        placeholder ="Введите пароль",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    Button(
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .fillMaxWidth()
                            .height(51.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF049C6B),
                            contentColor = Color.White
                        ),
                        onClick = {
                            focusManager.clearFocus()
                            if (state.value.loginText == "" || state.value.passwordText == "") {
                                isError = true
                                scope.launch { errorService.showError("Зполните все поля") }
                            } else {
                                viewModel.onAuthClick(
                                    state.value.loginText,
                                    state.value.passwordText
                                )
                            }
                        }) {
                        if (state.value.authLoadingState == LoadingState.Loading) {
                            CircularProgressIndicator(
                                Modifier.size(24.dp),
                                color = Color.White
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
