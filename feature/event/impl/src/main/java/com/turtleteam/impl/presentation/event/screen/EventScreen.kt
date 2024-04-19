package com.turtleteam.impl.presentation.event.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.impl.presentation.event.screen.components.QuizItem
import com.turtleteam.impl.presentation.event.viewModel.EventViewModel


data class DailyTask(
    val title: String,
    @DrawableRes
    val icon: Int,
    val points: Int
)

data class Achievements(
    @DrawableRes
    val image: Int,
    val background: Color,
)

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    viewModel: EventViewModel
) {
    val state = viewModel.state.collectAsState()
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val achievementsList1 = listOf(
        Achievements(
            image = R.drawable.ic_rocket,
            background = Color(0xFF452E6F)
        ),
        Achievements(
            image = R.drawable.ic_pig,
            background = Color(0xFFFF2E60)
        ),
        Achievements(
            image = R.drawable.ic_chat,
            background = Color(0xFFFFC01B)
        ),
        Achievements(
            image = R.drawable.ic_calendar,
            background = Color(0xFF4CA6FE)
        ),
        Achievements(
            image = R.drawable.ic_lock,
            background = Color(0xFFE5E5E5)
        )
    )

    val dailyTaskList = listOf(
        DailyTask(
            title = "Как1 правильно оценить состав пива",
            icon = R.drawable.ic_daily_task,
            points = 50
        ),
        DailyTask(
            title = "Как2 правильно",
            icon = R.drawable.ic_daily_task,
            points = 100
        ),
        DailyTask(
            title = "Как3 правильно оценить состав пива",
            icon = R.drawable.ic_daily_task,
            points = 30
        ),
        DailyTask(
            title = "Как4 правильно оценить состав пива",
            icon = R.drawable.ic_daily_task,
            points = 630
        ),
    )

    LazyColumn(
        modifier = modifier
            .drawBehind {
                drawCircle(
                    Color(0xFFED6B77),
                    radius = 2000f,
                    center = Offset(center.x, density.run { -(screenHeight.toFloat() * 1.8f) })
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(top = 150.dp)
                    .size(120.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null
                )
            }
        }

        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "${state.value.user?.user?.firstname} ${state.value.user?.user?.lastname}",
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(600),
            )
        }

        item {
            Column(Modifier.padding(top = 24.dp)) {
                Text(
                    text = "Достижения", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Row(Modifier.padding(top = 24.dp)) {
                    Spacer(modifier = Modifier.padding(end = 16.dp))
                    achievementsList1.forEach { achievements ->
                        AchievementsItem(
                            Modifier.padding(end = 16.dp),
                            image = achievements.image,
                            background = achievements.background
                        ) {

                        }
                    }
                }
                Row(
                    Modifier
                        .padding(top = 14.dp)
                ) {
                    Spacer(modifier = Modifier.padding(end = 16.dp))
                    repeat(6) {
                        AchievementsItem(
                            Modifier
                                .padding(end = 16.dp),
                            image = achievementsList1.last().image,
                            background = achievementsList1.last().background
                        ) {

                        }
                    }
                }
            }
        }

        item {
            Column(Modifier.padding(top = 30.dp)) {
                Text(
                    text = "Ежедневные Задания", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                LazyRow(
                    Modifier
                        .padding(top = 24.dp)
                ) {
                    item { Spacer(modifier = Modifier.padding(start = 16.dp)) }
                    items(items = dailyTaskList) { dailyTask ->
                        DailyTaskItem(
                            Modifier.padding(end = 16.dp),
                            title = dailyTask.title,
                            icon = dailyTask.icon,
                            points = dailyTask.points
                        ) {

                        }
                    }
                }
            }
        }
        if (state.value.quizIds.isNotEmpty()){
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 16.dp),
                    text = "Квизы",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
            }
            items(state.value.quizIds) {
                QuizItem(
                    modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 12.dp),
                    progress = 0.6f,
                    title = it.name
                ) {
                    viewModel.onQuizClick(it.quizId)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsItem(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    background: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.size(60.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClick() }
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = image), contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyTaskItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    points: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(170.dp)
            .height(IntrinsicSize.Max),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC01B)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClick() }
    ) {
        Column(Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 10.dp)) {
            Text(
                text = title,
                minLines = 2,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )

            Icon(
                painter = painterResource(id = icon), contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(46.dp)
                    .rotate(-18f)
                    .padding(top = 6.dp)
            )

            PointsItem(modifier = Modifier.padding(top = 5.dp), points = points)
        }
    }
}

@Composable
fun PointsItem(modifier: Modifier = Modifier, points: Int) {
    Row(
        modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White),
    ) {
        Row(
            Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_money), contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 3.dp)
            )

            Text(
                text = points.toString(), modifier = Modifier.padding(end = 2.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )

            Text(
                text = "Баллов", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFE5E5E5)
                ), modifier = Modifier.padding(end = 13.dp)
            )
        }
    }
}