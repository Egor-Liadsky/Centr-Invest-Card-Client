package com.turtleteam.impl.presentation.option.screen

import AdditionalView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.option.viewModel.OptionsViewModel

@Composable
fun OptionsScreen(
    modifier: Modifier = Modifier,
    viewModel: OptionsViewModel
) {

    Column(Modifier.fillMaxSize()) {

        Text(
            text = "Настройки",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.qanelas)),
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFF2A2F33)
            ), modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 10.dp)
        )

        AdditionalView(viewModel)
    }
}