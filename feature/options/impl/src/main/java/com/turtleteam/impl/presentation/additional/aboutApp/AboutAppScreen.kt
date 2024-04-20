package com.turtleteam.impl.presentation.additional.aboutApp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.topBar.CommonTopBar
import com.turtleteam.impl.presentation.option.viewModel.OptionsViewModel

@Composable
fun AboutAppScreen(viewModel: AboutAppViewModel) {
    Column(Modifier.fillMaxSize()) {

        Column(Modifier.background(Color(0xFFF1F3F5))) {
            CommonTopBar(
                title = "О приложении",
                backClick = { viewModel.onBackButtonClick() })
        }

        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Карта ростовчанина 1.0",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.qanelas)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF2A2F33),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}