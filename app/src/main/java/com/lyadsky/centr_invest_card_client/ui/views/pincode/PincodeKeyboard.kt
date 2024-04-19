package com.lyadsky.centr_invest_card_client.ui.views.pincode

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyadsky.centr_invest_card_client.R

@Composable
fun PincodeKeyboard(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onDoneClick: (() -> Unit)? = null
) {
    val numbers = listOf((1..3).toList(), (4..6).toList(), (7..9).toList())
    Column(
        modifier = Modifier
            .wrapContentSize()
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        numbers.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally)) {
                row.forEach {
                    PincodeButton(onClick = { onClick(it.toString()) }) {
                        Text(
                            text = it.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally)) {

            PincodeButton(
                enabled = onDoneClick != null,
                backGroundColor = if (onDoneClick != null) Color(0xFF4CAF50) else Color.Transparent,
                onClick = { onDoneClick?.invoke() }
            ) {
                if (onDoneClick != null)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_done),
                        contentDescription = null,
                        tint = Color.White
                    )
            }
            PincodeButton(onClick = {
                onClick("0")
            }) {
                Text(
                    text = "0",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            PincodeButton(
                backGroundColor = Color.White,
                onClick = { onDeleteClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_backspace),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun PincodeButton(
    backGroundColor: Color = Color(0xFFEFEFEF),
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(backGroundColor, CircleShape)
            .clip(CircleShape)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(color = Color(0xFF4CAF50)),
                enabled = enabled
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
