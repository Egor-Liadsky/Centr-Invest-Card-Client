package com.turtleteam.impl.presentation.welcome.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.welcome.viewModel.WelcomeViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "КАРТА\nРОСТОВЧАНИНА",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.despairdisplay_bold)),
                fontSize = 36.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.weight(1.2f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF04659C)
            ),
            onClick = { viewModel.onAuthClick() }
        ) {
            Text(
                text = "Авторизация"
            )
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(51.dp),
            onClick = { viewModel.onRegisterClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF04659C)
            )
        ) {
            Text(
                text = "Регистрация",
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.25f))
    }
}