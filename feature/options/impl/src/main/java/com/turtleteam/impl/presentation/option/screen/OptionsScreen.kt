package com.turtleteam.impl.presentation.option.screen

import AdditionalView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.turtleteam.core_view.R
import com.turtleteam.impl.R.drawable
import com.turtleteam.impl.presentation.option.viewModel.OptionsViewModel
import kotlin.math.max

@Composable
fun OptionsScreen(
    modifier: Modifier = Modifier,
    viewModel: OptionsViewModel
) {

    val state by viewModel.state.collectAsState()

    val textFieldStyle = TextStyle(fontSize = 16.sp)

    var emailText by rememberSaveable { mutableStateOf(state.user?.auth_hash ?: "") }
    var passwordText by rememberSaveable { mutableStateOf("") }

    // FIXME FROM VIEWMODEL
    var isPasswordHidden by remember { mutableStateOf(true) }
    var isPinCodeHidden by remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {

        Text(
            text = "Настройки",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.qanelas)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFF2A2F33)
            ), modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp)
        )

        Column(
            Modifier
                .fillMaxSize()
                .clipToBounds()
                .then(modifier)
                .verticalScroll(scrollState)
        ) {


            // FIXME FROM VIEWMODEL
            UserInfoLayout(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 30.dp
                ),
                rememberAsyncImagePainter(model = "https://yt3.googleusercontent.com/ytc/AIf8zZRKWtfS9EmKT96JGBh745BtyAoplTC-k6TIaIdVWg=s900-c-k-c0x00ffffff-no-rj"),
                fullname = "${state.userData.name} ${state.userData.family} \n ${state.userData.two_name}"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp, vertical = 20.dp),
            ) {
                Text(
                    text = "Аккаунт",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
                    text = "Email",
                    color = Color(0xFFA7ACAF),
                    fontSize = 12.sp
                )
                OptionsTextField(
                    value = emailText,
                    style = textFieldStyle,
                    onValueChange = {
                        emailText = it
                    }
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
                    text = "Пароль",
                    color = Color(0xFFA7ACAF),
                    fontSize = 12.sp
                )
                OptionsTextField(
                    value = passwordText,
                    style = textFieldStyle,
                    isHidden = isPasswordHidden,
                    onHideClick = { isPasswordHidden = !isPasswordHidden },
                    onValueChange = {
                        passwordText = it
                    },
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp, bottom = 5.dp),
                    text = "PIN-CODE",
                    color = Color(0xFFA7ACAF),
                    fontSize = 12.sp
                )
                OptionsTextField(
                    value = state.pinCode ?: "",
                    style = textFieldStyle,
                    isHidden = !isPinCodeHidden,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onHideClick = { isPinCodeHidden = !isPinCodeHidden },
                    onValueChange = {}
                )
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2A2F33)
                )
            ) {
                Text(
                    text = "Привязать страховой полис",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.qanelas)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }

            AdditionalView(viewModel)
        }
    }
}

@Composable
fun OptionsTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isHidden: Boolean? = null,
    onHideClick: (() -> Unit)? = null,
    readOnly: Boolean = true,
    enabled: Boolean = false,
    style: TextStyle = TextStyle(),
    backgroundColor: Color = Color(0xFFF1F3F5),
    shape: Shape = RoundedCornerShape(5.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    BasicTextField(
        modifier = Modifier
            .sizeIn(minWidth = 120.dp, minHeight = 50.dp)
            .background(backgroundColor, shape)
            .then(modifier),
        value = value,
        textStyle = style,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = isHidden != null,
        onValueChange = onValueChange,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isHidden != null) {
                    Box(modifier = Modifier.weight(1f)) {
                        TextLayout(
                            isHidden,
                            { it() },
                            {
                                Text(
                                    text = buildString { repeat(value.length) { append("•") } },
                                    maxLines = 1
                                )
                            }
                        )
                    }
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onHideClick ?: {}
                        ),
                        painter = painterResource(id = if (isHidden) drawable.hide else drawable.show),
                        contentDescription = ""
                    )
                } else it()
            }
        }
    )
}

@Composable
fun TextLayout(
    hidden: Boolean = false,
    placeholder: @Composable () -> Unit,
    text: @Composable () -> Unit
) {
    Layout(
        modifier = Modifier,
        content = {
            placeholder()
            text()
        },
        measurePolicy = { measurables, constraints ->
            val placeholderPlaceable = measurables.first().measure(constraints)
            val textPlaceable = measurables.last().measure(constraints)

            val height = max(textPlaceable.height, placeholderPlaceable.height)
            val width = max(textPlaceable.width, placeholderPlaceable.width)
            layout(width, height) {
                placeholderPlaceable.placeRelativeWithLayer(0, 0) {
                    alpha = if (hidden) 1f else 0f
                }
                textPlaceable.placeRelativeWithLayer(0, 0) {
                    alpha = if (hidden) 0f else 1f
                }
            }
        })
}

@Composable
fun UserInfoLayout(
    modifier: Modifier,
    imgPainter: AsyncImagePainter,
    fullname: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        Image(
            modifier = Modifier
                .size(78.dp)
                .clip(CircleShape),
            painter = imgPainter, contentDescription = ""
        )
        Text(
            text = fullname,
            fontWeight = 700.weight,
            fontSize = 20.sp
        )
    }

}

val Int.weight
    get() = FontWeight(this)