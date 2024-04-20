package com.turtleteam.core_view.view.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@SuppressLint("SimpleDateFormat", "UnrememberedMutableInteractionSource")
@Composable
fun DetailCardInfo(
    owner: String,
    cash: Float,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Box(
        Modifier
            .then(modifier)
            .height(194.dp)
            .clip(RoundedCornerShape(10.dp))
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

        Image(
            painter = painterResource(id = R.drawable.ic_card_background),
            contentDescription = null,
            Modifier.fillMaxSize()
        )

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 15.dp, top = 21.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "КАРТА\nРОСТОВЧАНИНА",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.despairdisplay_bold)),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    ),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp, start = 26.dp, end = 26.dp, bottom = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = "Имя владельца",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        ),
                    )
                    Text(
                        text = owner,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }


                Column {
                    Text(
                        text = "Баланс",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        ),
                    )
                    Text(
                        text = "${cash}₽",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat", "UnrememberedMutableInteractionSource")
@Composable
fun EmptyCardInfo(
    modifier: Modifier = Modifier,
) {
    Box(
        Modifier
            .then(modifier)
            .height(194.dp)
            .clip(RoundedCornerShape(10.dp))
            .drawBehind {
                drawCircle(
                    color = Color(0xFF049C6B),
                    center = Offset(y = size.height * 1.5f, x = size.width * 0.3f),
                    radius = 800f,
                    blendMode = BlendMode.Overlay
                )
            }

    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_card_background),
            contentDescription = null,
            Modifier.fillMaxSize()
        )

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 15.dp, top = 21.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "КАРТА\nРОСТОВЧАНИНА",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.despairdisplay_bold)),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    ),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp, start = 26.dp, end = 26.dp, bottom = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = "Необходимо оформить",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        ),
                    )
                }
            }
        }
    }
}
