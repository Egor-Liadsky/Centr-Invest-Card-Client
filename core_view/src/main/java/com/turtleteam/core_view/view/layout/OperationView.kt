package com.turtleteam.core_view.view.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OperationView(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text(
                text = "Крайнюков А.",
                fontSize = 14.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(600),
            )
            Text(
                text = "Переводы",
                fontSize = 12.sp,
                lineHeight = 28.sp,
            )
        }
        Text(
            text = "-85 ₽ ",
            fontSize = 14.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(600),
        )
    }
}