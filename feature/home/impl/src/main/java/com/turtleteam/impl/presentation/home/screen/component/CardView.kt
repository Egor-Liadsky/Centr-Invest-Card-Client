package com.turtleteam.impl.presentation.home.screen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.api.model.Bill
import com.turtleteam.api.api.model.Card
import com.turtleteam.core_view.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardView(
    modifier: Modifier = Modifier,
    card: Card,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick() },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 23.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = card.bill.title,
                    fontSize = 12.sp,
                    letterSpacing = 0.12.sp
                )
                Text(
                    text = card.bill.remainder + " руб",
                    fontSize = 12.sp,
                    letterSpacing = 0.12.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.card_image),
                contentDescription = ""
            )
        }
    }
}

val cardList = listOf(
    Card(
        numCards = "2200 1706 0156 6172",
        dateClose = "08/28",
        code = "231",
        limitBegin = "10 000",
        limitEnd = "3 723",
        bill = Bill(
            title = "ГБПОУ РО \"РКСИ\" сотрудники",
            owner = "Egor Lyadsky",
            number = "",
            dateOpen = "09.08.2023",
            agreement = "",
            remainder = "91 234"
        )
    ),
    Card(
        numCards = "2200 3213 5345 1233",
        dateClose = "08/28",
        code = "231",
        limitBegin = "75 000",
        limitEnd = "42 712",
        bill = Bill(
            title = "ГБПОУ РО \"РИНХ\" сотрудники",
            owner = "Egor Lyadsky",
            number = "",
            dateOpen = "09.08.2023",
            agreement = "",
            remainder = "1 275"
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallCardView(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(83.dp)
            .height(69.dp)
            .then(modifier),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Spacer(modifier = Modifier.weight(1.4f))
        Image(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight(600),
            lineHeight = 12.sp
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}
