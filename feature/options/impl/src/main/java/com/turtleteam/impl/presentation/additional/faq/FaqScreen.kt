package com.turtleteam.impl.presentation.additional.faq

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.topBar.CommonTopBar

@Composable
fun FaqScreen(viewModel: FaqViewModel) {
    Column(Modifier.fillMaxSize()) {

        val faqList = listOf(
            "Общая информация о карте ростовчанина",
            "Конфиденциальность и безопасность",
            "Как вы обрабатываете мои данные?",
            "Мобильное приложение",
            "Социальные сети"
        )

        Column(Modifier.background(Color(0xFFF1F3F5))) {
            CommonTopBar(
                title = "FAQ",
                backClick = { viewModel.onBackButtonClick() })
        }

        LazyColumn(Modifier.padding(16.dp)) {
            item {
                faqList.forEach {
                    FaqItem(title = it, expanded = false) {

                    }
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }   
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqItem(modifier: Modifier = Modifier, title: String, expanded: Boolean, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, Color(0xFFE1E1E1), RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {}
    ) {
        Row(
            Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (expanded) "-" else "+", style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.qanelas)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF04659C),
                )
            )
            Text(
                title, style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.qanelas)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color(0xFF2A2F33),
                ), modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}