package com.turtleteam.core_view.view.groupbuttons

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProfileButton(
    @DrawableRes val icon: Int? = null,
    val leadingText: String,
    val trailingText: String,
    val action: () -> Unit
)

@Composable
fun ButtonsGroup(
    modifier: Modifier = Modifier,
    buttons: List<ProfileButton>
) {
    val shape = RoundedCornerShape(8.dp)
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .background(Color.White, shape = shape)
            .padding(vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        buttons.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(color = Color(0xFF4CAF50))
                    ) { it.action() }
                    .padding(vertical = 3.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (it.icon == null)
                    Spacer(modifier = Modifier.size(24.dp))
                else
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.leadingText
                    )
                Text(
                    modifier = Modifier.padding(start = 13.dp),
                    text = it.leadingText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    letterSpacing = 0.25.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = it.trailingText,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    letterSpacing = 0.25.sp,
                    color = Color(0xFF00602A),
                )
            }
        }
    }
}