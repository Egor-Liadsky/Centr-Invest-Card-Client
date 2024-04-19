package com.turtleteam.impl.presentation.register.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel
) {

    val state = viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Column {
        MediumTopAppBar(
            title = { Text(text = "Регистрация") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFBEFF6C)
            ),//TODO fix hardcode
            navigationIcon = {
                IconButton(onClick = { viewModel.onBackButtonClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            },
            scrollBehavior = scrollBehavior
        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .pointerInput(Unit) {
                    detectTapGestures { focusManager.clearFocus() }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 40.dp)
        ) {

            item {
                OutlinedTextField(
                    value = state.value.loginText,
                    singleLine = true,
                    onValueChange = { viewModel.onLoginTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = state.value.loginError,
                    placeholder = { Text("Введите логин") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = state.value.firstNameText,
                    singleLine = true,
                    onValueChange = { viewModel.onFirstNameTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = state.value.firstNameError,
                    placeholder = { Text("Введите имя") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {

                OutlinedTextField(
                    value = state.value.lastNameText,
                    singleLine = true,
                    onValueChange = { viewModel.onLastNameTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = state.value.lastNameError,
                    placeholder = { Text("Введите фамилию") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = state.value.emailText,
                    singleLine = true,
                    onValueChange = { viewModel.onEmailTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = state.value.emailError,
                    placeholder = { Text("Введите почту") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {

                OutlinedTextField(
                    value = state.value.passwordText,
                    visualTransformation = if (state.value.isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { viewModel.onHidePasswordClick() }) {
                            val iconPainter =
                                painterResource(id = if (state.value.isPasswordHidden) R.drawable.ic_visibility else R.drawable.ic_visibility_off)
                            Icon(painter = iconPainter, contentDescription = null)
                        }
                    },
                    singleLine = true,
                    onValueChange = { viewModel.onPasswordTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isError = state.value.passwordError,
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    placeholder = { Text("Введите пароль") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.value.checkBoxEnabled,
                            onCheckedChange = { viewModel.onCheckBoxClick() }
                        )
                        Text(
                            text = "Я согласен с Политикой конфиденциальности",
                            fontSize = 8.sp,
                            color = Color(0xFF6750A4), //TODO fix hardcode
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                Log.e("ajshd", "click")
                            }
                        )
                    }

                    Button(
                        modifier = Modifier.width(114.dp),
                        onClick = {
                            focusManager.clearFocus()
                            viewModel.onRegisterClick(
                                UserDTOReceive(
                                    login = state.value.loginText,
                                    password = state.value.passwordText
                                )
                            )
                        }) {
                        if (state.value.registerLoadingState == LoadingState.Loading) {
                            CircularProgressIndicator(Modifier.size(24.dp), color = Color.White)
                        } else {
                            Text(text = "Далее")
                        }
                    }
                }
            }
        }
    }
}
