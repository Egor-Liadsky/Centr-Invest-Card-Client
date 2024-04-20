package com.turtleteam.core_view.view.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.button.SquareButton

@Composable
fun CommonTopBar(
    title: String,
    backClick: () -> Unit? = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF1F3F5))
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SquareButton(icon = painterResource(id = R.drawable.ic_back_button), iconSize = 15.dp) {
            backClick()
        }

        Text(
            text = title,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.font_qanelas)),
                fontWeight = FontWeight.SemiBold,
                fontSize =  20.sp,
                color = Color(0xFF2A2F33),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}