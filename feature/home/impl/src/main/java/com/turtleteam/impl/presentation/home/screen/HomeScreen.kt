package com.turtleteam.impl.presentation.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.R
import com.turtleteam.core_view.utils.categoryIcon
import com.turtleteam.core_view.view.cards.DetailCardInfo
import com.turtleteam.core_view.view.cards.EmptyCardInfo
import com.turtleteam.core_view.view.layout.Refreshable
import com.turtleteam.impl.presentation.home.screen.component.HistorySheetLayout
import com.turtleteam.impl.presentation.home.screen.component.ServiceItemView
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val progress = remember { mutableFloatStateOf(0f) }
    val state = viewModel.state.collectAsState()
    val pagerState =
        rememberPagerState { if (state.value.cards?.size == 0) 1 else state.value.cards?.size ?: 1 }

    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {

            val data =
                state.value.serviceHistory?.filter { it.category_id == state.value.selectedPrivileges?.id }
            HistorySheetLayout(
                modifier = Modifier.fillMaxSize(),
                data = data ?: listOf(),
                loadingState = state.value.serviceLoadingState,
                icon = state.value.selectedPrivileges?.id?.categoryIcon()
                    ?: R.drawable.ic_not_category,
                title = state.value.selectedPrivileges?.name ?: "Операции"
            )
        }
    ) {

        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier)
                .clipToBounds(),
            toolbarModifier = Modifier.background(
                Color(0xFF04659C),
                RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
            ),
            state = rememberCollapsingToolbarScaffoldState(),
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val scale = (progress.floatValue * 10f) * 0.02f
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .progress { progress.floatValue = it }
                        .alpha(progress.floatValue)
                        .padding(top = 70.dp)
                        .scale(0.8f + scale)
                        .offset(y = (-10).dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        pageSpacing = 16.dp
                    ) {
                        if (state.value.cards?.size != 0) {
                            DetailCardInfo(
                                owner = "${state.value.userProfile.name} ${state.value.userProfile.family}",
                                cash = state.value.userProfile.cash,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp),
                            ) {
                                state.value.cards?.get(0)?.let {
                                    viewModel.navigateToDetailCard(cardId = it.key)
                                }
                            }
                        } else {
                            EmptyCardInfo(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = 24.dp),
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .pin()
                        .padding(top = 20.dp, bottom = 17.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_ava), contentDescription = ""
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "${state.value.userProfile.name} ${state.value.userProfile.family}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )
                }
            }) {

//            Refreshable(
//                isRefreshing = state.value.isRefreshing,
//                onRefresh = { viewModel.getPrivileges() }) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White,
                            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    contentPadding = PaddingValues(vertical = 28.dp)
                ) {

                    item {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 20.dp),
                            text = "Услуги",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                        )
                    }
                    when (state.value.categoriesLoadingState) {

                        LoadingState.Loading -> {
                            item {
                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator(
                                        Modifier.size(24.dp),
                                        color = Color(0xFF2A2F33)
                                    )
                                }
                            }
                        }

                        LoadingState.Success -> {
                            items(items = state.value.categories ?: listOf()) { category ->
                                ServiceItemView(
                                    Modifier.padding(horizontal = 16.dp),
                                    title = category.name,
                                    icon = category.id.categoryIcon()
                                ) {
                                    viewModel.onPrivilegesClick(category)
                                    scope.launch {
                                        modalBottomSheetState.show()
                                    }
                                }
                                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                            }
                        }

                        is LoadingState.Error -> {

                        }

                        LoadingState.Empty -> TODO()
                    }
                }
//            }
        }
    }
}
