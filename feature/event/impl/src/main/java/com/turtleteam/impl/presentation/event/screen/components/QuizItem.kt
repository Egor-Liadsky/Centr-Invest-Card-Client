package com.turtleteam.impl.presentation.event.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R.*
import com.turtleteam.impl.R

@Composable
fun QuizItem(
    modifier: Modifier = Modifier,
    progress: Float = 0.4f,
    title: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(20.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        )
    ) {
        Image(
            modifier = Modifier.size(62.dp, 74.dp),
            painter = painterResource(id = drawable.image_turtle),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(600),
                    color = Color.Black
                )
                Text(
                    modifier =Modifier.width(60.dp),
                    text = (progress * 100).toInt().toString()+"%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFED6B77),
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Тестовый тест",
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFE5E5E5),
            )
            Spacer(modifier = Modifier.height(24.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .clip(RoundedCornerShape(10.dp)),
                progress = progress,
                color = Color(0xFFED6B77),
                trackColor = Color(0xFFE5E5E5)
            )
        }
    }
}