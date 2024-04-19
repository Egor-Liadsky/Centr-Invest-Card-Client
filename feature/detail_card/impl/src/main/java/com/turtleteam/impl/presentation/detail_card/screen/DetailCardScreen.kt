package com.turtleteam.impl.presentation.detail_card.screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
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
    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8F5E9))
            .clipToBounds(),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            val scale = (progress.floatValue * 10f) * 0.02f
            IconButton(
                modifier = Modifier
                    .pin(),
                onClick = { viewModel.onBackButtonClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    Modifier.size(24.dp)
                )
            }

            CardInfo(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = (screenWidth * 0.1f).dp)
                    .padding(vertical = (screenWidth * 0.05f).dp)
                    .alpha(progress.floatValue)
                    .scale(0.8f + scale)
                    .progress {
                        progress.floatValue = it
                    },
                name = "${state.value.user?.user?.firstname} ${state.value.user?.user?.lastname}"
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
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    LimitView(limitBegin = 100, limitEnd = 70)
                }
            }

            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp)
                ) {
                    RequisitesView(
                        numberCode = if (state.value.isDetailsShown) cardId else cardId.hideCardNum(),
                        date = if (state.value.isDetailsShown)state.value.cardDate else hiddenDate(),
                        code = if (state.value.isDetailsShown)state.value.cardCvc else "•••"
                    ){viewModel.onShowRequisites()}
                }
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 30.dp),
                    text = "История операций",
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(600),
                )
            }

            items(count = 8) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Крайнюков А.",
                            fontSize = 14.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(600),
                        )
                        Text(
                            text = "Переводы",
                            fontSize = 12.sp,
                            lineHeight = 28.sp,
                        )
                    }
                    Text(
                        text = "-85 ₽ ",
                        fontSize = 14.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                    )
                }
            }
        }
    }
}

@Composable
fun LimitView(limitBegin: Int, limitEnd: Int) {
    Card(
        Modifier
            .fillMaxWidth()
            .background(Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Твой лимит - $limitBegin ₽",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = "Потрачено $limitEnd ₽",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 15.dp)
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                progress = (limitEnd.toFloat() / limitBegin.toFloat()),
                color = Color(0xFF4DB45F),
                trackColor = Color(0xFFEFEFEF),
            )
        }
    }
}

@Composable
fun RequisitesView(numberCode: String, date: String, code: String, onShow:()->Unit) {
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
                fontWeight = FontWeight.SemiBold
            )

            TextButton(onClick = { onShow() }) {
                Text(
                    text = "Показать",
                    fontSize = 10.sp,
                    color = Color.Black,
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
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEFEFEF))
            .padding(horizontal = 10.dp, vertical = 14.dp)
    )
}

@Composable
fun CardInfo(modifier: Modifier = Modifier, name: String) {
    Column(
        Modifier
            .then(modifier)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF4DB45F))
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
                .padding(top = 76.dp, start = 26.dp, bottom = 22.dp, end = 26.dp)
        ) {
            Text(
                text = "Имя владельца",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ),
            )
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )
        }
    }
}