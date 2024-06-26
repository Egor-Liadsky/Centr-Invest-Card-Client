package com.turtleteam.core_view.view.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SquareButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    iconColor: Color = Color(0xFF2A2F33),
    iconSize: Dp = 20.dp,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(5.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = "ic_square_button",
                modifier = Modifier.size(iconSize),
                tint = iconColor
            )
        }
    }
}