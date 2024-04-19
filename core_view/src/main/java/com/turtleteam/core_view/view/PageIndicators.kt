package com.turtleteam.core_view.view

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    indicatorColor: Color = Color(0xFFB2B2B2),
    currentPage: Int,
    count: Int
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(count) {
            val size = if (currentPage == it) 25f else 10f
            val width = remember { Animatable(size) }
            Canvas(modifier = Modifier.height(10.dp).width(width.value.dp), onDraw = {
                drawRoundRect(indicatorColor, cornerRadius = CornerRadius(100f,100f))
            })
            LaunchedEffect(key1 = currentPage, block = {
                width.animateTo(size)
            })
        }
    }
}