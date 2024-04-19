package com.turtleteam.impl.presentation.event.quiz

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R.*
import com.turtleteam.impl.presentation.event.quiz.component.StageBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    modifier: Modifier,
    viewModel: QuizViewModel
) {
    val state = viewModel.state.collectAsState()
    val pager = rememberPagerState { (state.value.quiz?.answers?.size)?: 1 }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFED6B77))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { viewModel.onBackClick() }) {
                Icon(
                    painter = painterResource(id = drawable.ic_back),
                    tint = Color.White,
                    contentDescription = null
                )
            }
            Text(
                text = state.value.quiz?.name ?: "",
                fontSize = 20.sp,
                minLines = 2,
                fontWeight = FontWeight(600),
                color = Color.White,
            )
        }
        StageBar(
            modifier = Modifier.padding(vertical = 12.dp),
            number = pager.currentPage + 1,
            count = pager.pageCount
        )
        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, RoundedCornerShape(topEnd = 18.dp, topStart = 18.dp))
                .clip(RoundedCornerShape(topEnd = 18.dp, topStart = 18.dp)),
            pageSpacing = 16.dp,
            verticalAlignment = Alignment.Top,
            state = pager,
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
            ) {
                item {
                    Text(
                        text = state.value.quiz?.teachingText ?: "",
                        fontSize = 24.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                    )
                }
            }
        }
    }
}