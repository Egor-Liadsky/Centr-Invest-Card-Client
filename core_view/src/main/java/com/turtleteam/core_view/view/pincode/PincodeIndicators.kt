package com.turtleteam.core_view.view.pincode

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PincodeIndicators(
    modifier: Modifier = Modifier,
    pincode: Int,
    length: Int,
){
    val enabledColor = Color(0xFF4CAF50)
    val disabledColor = Color(0xFFEFEFEF)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)) {
        repeat(length) {
            val color = remember { Animatable(if (pincode <= it+1) disabledColor else enabledColor) }
            Box(modifier = Modifier
                .size(24.dp)
                .background(color.value, CircleShape))
            LaunchedEffect(key1 = pincode, block = {
                color.animateTo(if (pincode < it+1) disabledColor else enabledColor)
            })
        }
    }
}