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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.api.model.UserDTOReceive
import com.turtleteam.core_navigation.error.ErrorService
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.textfields.CustomTextField
import com.turtleteam.impl.presentation.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import org.koin.androidx.compose.get
import org.koin.compose.koinInject

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel
) {
    val errorService: ErrorService = koinInject()
    val state = viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF04659C))
            .clipToBounds(),
        toolbarModifier = Modifier.background(Color(0xFF04659C)),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF04659C))
                    .pin(),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { viewModel.onBackButtonClick() }) {
                        Icon(
                            tint = Color.White,
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier.padding(bottom = 30.dp, start = 16.dp),
                        text = "Регистрация",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
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
                Column(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)) {
                    CustomTextField(
                        value = state.value.lastNameText,
                        icon = null,
                        singleLine = true,
                        onValueChange = { viewModel.onLastNameTextChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = "Фамилия",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    CustomTextField(
                        value = state.value.firstNameText,
                        icon = null,
                        singleLine = true,
                        onValueChange = { viewModel.onFirstNameTextChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = "Имя",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    CustomTextField(
                        value = state.value.surnameText,
                        icon = null,
                        singleLine = true,
                        onValueChange = { viewModel.onSurnameTextChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = "Отчество",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    CustomTextField(
                        value = state.value.loginText,
                        icon = null,
                        singleLine = true,
                        onValueChange = { viewModel.onLoginTextChanged(it) },
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
                        placeholder = "Пароль",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )
                }
            }

            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.value.checkBoxEnabled,
                            onCheckedChange = { viewModel.onCheckBoxClick() },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF04659C),
                            )
                        )
                        Text(
                            text = "Я согласен с Политикой конфиденциальности",
                            fontSize = 12.sp,
                            color = Color(0xFF04659C),
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) {
                                Log.e("ajshd", "click")
                            }
                        )
                    }

                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            viewModel.onRegisterClick(
                                UserDTOReceive(
                                    state.value.lastNameText,
                                    state.value.firstNameText,
                                    state.value.surnameText,
                                    state.value.loginText,
                                    state.value.passwordText
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2A2F33)
                        )
                    ) {
                        if (state.value.registerLoadingState == LoadingState.Loading) {
                            CircularProgressIndicator(Modifier.size(24.dp), color = Color.White)
                        } else {
                            Text(
                                text = "Зарегистррироваться",
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.qanelas)),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = Color.White
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}
