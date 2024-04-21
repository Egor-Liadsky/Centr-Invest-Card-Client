package com.turtleteam.core_view.view.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@Composable
fun EmptyLayout(modifier: Modifier = Modifier) {

    Column(
        modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_empty),
            contentDescription = null,
            tint = Color(0xFF04659C),
            modifier = Modifier
                .size(70.dp, 65.dp)
                .padding(bottom = 15.dp)
        )

        Text(
            text = "Операций пока не было",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.qanelas)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color(0xFF2A2F33)
            )
        )
    }
}