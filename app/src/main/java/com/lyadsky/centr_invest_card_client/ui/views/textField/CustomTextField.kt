package com.lyadsky.centr_invest_card_client.ui.views.textField

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    iconColor: Color = Color(0xFF00602A),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @DrawableRes icon: Int? = null,
    placeholder: String,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, Color(0xFFD9D9D9)),
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            fontSize = 12.sp,
            lineHeight = 28.sp,
            color = Color.Black,
        ),
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        tint = iconColor
                    )
                } else Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (value.isEmpty())
                        Text(
                            text = placeholder,
                            fontSize = 12.sp,
                            lineHeight = 28.sp,
                            color = Color(0x4D1F1F1F),
                        )
                    innerTextField()
                }
                trailingIcon?.let {
                    trailingIcon()
                }
            }
        }
    )
}