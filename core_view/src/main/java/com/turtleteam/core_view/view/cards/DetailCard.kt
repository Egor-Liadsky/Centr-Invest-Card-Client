package com.turtleteam.core_view.view.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun DetailCardInfo(
    modifier: Modifier = Modifier,
    name: String,
    cardNumber: String,
    date: String,
    onClick: (() -> Unit)? = null
) {
    Column(
        Modifier
            .then(modifier)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF4DB45F))
            .drawBehind {
                drawCircle(
                    color = Color(0xFF049C6B),
                    center = Offset(y = size.height * 1.5f, x = size.width * 0.3f),
                    radius = 800f,
                    blendMode = BlendMode.Overlay
                )
            }
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                enabled = onClick != null
            ) {
                onClick?.invoke()
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 15.dp, top = 21.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plata), contentDescription = null,
                modifier = Modifier.size(width = 60.dp, height = 40.dp),
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_mir), contentDescription = null,
                modifier = Modifier.size(width = 62.dp, height = 18.dp),
                tint = Color.White
            )
        }


        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 26.dp, start = 26.dp)
        ) {
            Text(
                text = "Номер карты",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ),
            )
            Text(
                text = cardNumber,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 26.dp, start = 26.dp, end = 26.dp, bottom = 22.dp)
        ) {
            val formattedDate = date.replace(Regex("""(\d{4})-(\d{2})-(\d{2})"""), "$3/$2")

            Spacer(modifier = Modifier.weight(1f))

            Column {
                Text(
                    text = "Дата истечения",
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    ),
                )
                Text(
                    text = formattedDate,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )
            }
        }
    }
}