package com.turtleteam.impl.presentation.profile.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R.*
import com.turtleteam.core_view.view.groupbuttons.ButtonsGroup
import com.turtleteam.impl.presentation.profile.screen.components.getButtonsList
import com.turtleteam.impl.presentation.profile.viewModel.ProfileViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    val state = viewModel.state.collectAsState()
    val btns = getButtonsList(viewModel = viewModel)
    val density = LocalDensity.current
    val screenHeight = LocalConfiguration.current.screenHeightDp
    LazyColumn(
        modifier = modifier
            .drawBehind {
                    drawCircle(Color(0xFFE8F5E9), radius = 2000f, center = Offset(center.x,density.run { -(screenHeight.toFloat()*1.8f) }))
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        stickyHeader {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = drawable.ic_notifications),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = drawable.ic_history),
                        contentDescription = ""
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = drawable.ic_more_actions),
                        contentDescription = ""
                    )
                }
            }
        }
        item {
            Box(
                modifier = Modifier
                    .padding(top = 70.dp)
                    .size(120.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    painter = painterResource(id = drawable.profile),
                    contentDescription = null
                )
                IconButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = { /*TODO*/ }) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(Color(0xFFF5F5F5), CircleShape),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(22.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = drawable.ic_edit),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Егор Лядский",
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(600),
            )
        }
        itemsIndexed(btns) { index, item ->
            ButtonsGroup(
                modifier = Modifier
                    .padding(top = if (index == 0) 34.dp else 24.dp)
                    .padding(horizontal = 16.dp),
                buttons = item
            )
        }
    }
}