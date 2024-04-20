package com.turtleteam.impl.presentation.home.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.core_view.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceItemView(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier,
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        border = BorderStroke(1.dp, Color(0xFFD9D9D9)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

                Text(text = title)
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "ic arrow right",
                tint = Color.Black,
            )
        }
    }
}
