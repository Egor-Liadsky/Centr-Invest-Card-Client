package com.turtleteam.core_view.view.textfields

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    iconColor: Color = Color(0xFF00602A),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @DrawableRes icon: Int? = R.drawable.ic_phone,
    singleLine: Boolean = false,
    placeholder: String,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 28.sp,
            fontFamily = FontFamily(Font(R.font.qanelas)),
            color = Color(0xFF2A2F33)
        ),
        singleLine = singleLine,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFD9D9D9), shape = RoundedCornerShape(10.dp))
                    .background(backgroundColor)
                    .padding(horizontal = 10.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = icon ?: R.drawable.ic_phone),
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
                            fontSize = 14.sp,
                            lineHeight = 28.sp,
                            fontFamily = FontFamily(Font(R.font.qanelas)),
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