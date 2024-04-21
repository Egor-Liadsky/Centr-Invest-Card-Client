package com.turtleteam.core_view.view.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.models.ServiceHistory
import com.turtleteam.core_view.utils.categoryIcon

@Composable
fun ServiceHistoryItemView(
    modifier: Modifier = Modifier,
    service: ServiceHistory
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
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
                    painter = painterResource(id = service.category_id.categoryIcon()),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = service.reason,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF2A2F33)
                        ),
                    )
                    Text(
                        text = service.date,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF919191)
                        ),
                    )
                }
            }

            Text(
                text = "${service.price.toInt()} ₽",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.qanelas)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF2A2F33)
                ),
            )
        }
    }
}