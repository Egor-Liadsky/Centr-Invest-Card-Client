package com.turtleteam.core_view.view.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Refreshable(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
    Box(
        Modifier.pullRefresh(pullRefreshState)
    ) {
        content()
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
            contentColor = Color(0xFF04659C),
            backgroundColor = Color.White
        )
    }
}