package com.turtleteam.impl.presentation.additional.aboutApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.topBar.CommonTopBar

@Composable
fun AboutAppScreen() {
    Column(Modifier.fillMaxSize()) {

        Column(Modifier.background(Color(0xFFF1F3F5))) {
            CommonTopBar(
                title = "О приложении",
                backClick = {  })
        }

        Text(
            text = "Настройки",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.qanelas)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFF2A2F33)
            ), modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 10.dp)
        )
    }
}