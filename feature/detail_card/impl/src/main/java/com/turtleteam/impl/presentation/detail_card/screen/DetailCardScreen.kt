package com.turtleteam.impl.presentation.detail_card.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.cards.DetailCardInfo
import com.turtleteam.core_view.view.cards.ServiceHistoryItemView
import com.turtleteam.core_view.view.hiddenDate
import com.turtleteam.core_view.view.hideCardNum
import com.turtleteam.impl.presentation.detail_card.viewModel.DetailCardViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun DetailCardScreen(
    cardId: String,
    viewModel: DetailCardViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val progress = remember { mutableFloatStateOf(0f) }
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.centrinvest.ru"))

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF04659C))
            .clipToBounds(),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val scale = (progress.floatValue * 10f) * 0.02f
            IconButton(
                modifier = Modifier.pin(),
                onClick = { viewModel.onBackButtonClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    Modifier.size(24.dp),
                    tint = Color.White
                )
            }

            DetailCardInfo(
                owner = "${state.value.userData.name} ${state.value.userData.surname}",
                cash = state.value.userData.cash,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = (screenWidth * 0.1f).dp)
                    .padding(vertical = (screenWidth * 0.05f).dp)
                    .alpha(progress.floatValue)
                    .scale(0.8f + scale)
                    .progress {
                        progress.floatValue = it
                    },
            )

        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {

            item {
                Button(
                    onClick = { context.startActivity(intent) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2A2F33)
                    )
                ) {
                    Text(
                        text = "Пополнить",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.qanelas)),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }

            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 20.dp)
                ) {
                    RequisitesView(
                        numberCode = if (state.value.isDetailsShown) cardId else cardId.hideCardNum(),
                        date = if (state.value.isDetailsShown) state.value.cardDate else hiddenDate(),
                        code = if (state.value.isDetailsShown) state.value.cardCvc else "•••"
                    ) { viewModel.onShowRequisites() }
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 30.dp),
                    text = "История операций",
                    fontFamily = FontFamily(Font(R.font.qanelas)),
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(600),
                )
            }

            items(count = 8) {
                ServiceHistoryItemView(
                    title = "Транспорт Ростова-на-Дону",
                    description = "20.04.2024 в 15:45",
                    history = "Транспорт Ростова-на-Дону",
                    icon = R.drawable.ic_privileges
                )
            }
        }
    }
}

@Composable
fun RequisitesView(numberCode: String, date: String, code: String, onShow: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Реквизиты карты",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.qanelas)),
                fontWeight = FontWeight.SemiBold
            )

            TextButton(onClick = { onShow() }) {
                Text(
                    text = "Показать",
                    fontSize = 10.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.qanelas)),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 17.dp)
        ) {
            HiddenText(Modifier.fillMaxWidth(), text = numberCode)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HiddenText(Modifier.weight(0.5f), text = code)
                Spacer(modifier = Modifier.size(10.dp))
                HiddenText(Modifier.weight(0.5f), text = date)
            }
        }
    }
}

@Composable
fun HiddenText(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.qanelas)),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEFEFEF))
            .padding(horizontal = 10.dp, vertical = 14.dp)
    )
}
