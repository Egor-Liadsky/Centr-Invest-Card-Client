package com.turtleteam.impl.presentation.home.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TextButton
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.turtleteam.core_view.R
import com.turtleteam.core_view.R.drawable
import com.turtleteam.core_view.models.Operation
import com.turtleteam.core_view.view.PageIndicator
import com.turtleteam.core_view.view.bottomSheet.OperationBottomSheet
import com.turtleteam.core_view.view.cards.DetailCardInfo
import com.turtleteam.impl.presentation.home.screen.component.HistorySheetLayout
import com.turtleteam.impl.presentation.home.screen.component.ServiceItemView
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel
import com.turtleteam.impl.presentation.home.viewModel.Services
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

data class Service(
    val title: String,
    @DrawableRes
    val icon: Int,
    val type: Services
)

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
    val showBottomSheet = remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsState()
    val pagerState = rememberPagerState { state.value.cards?.size ?: 1 }
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {

            when(state.value.selectedService) {
                Services.Medical -> {
                    HistorySheetLayout(
                        modifier = Modifier.fillMaxSize(),
                        data = state.value.privileges ?: listOf(),
                        loadingState = state.value.privilegesLoadingState,
                        icon = R.drawable.ic_medical,
                        title = "Медицина"
                    )
                }
                Services.Privileges -> {
                    HistorySheetLayout(
                        modifier = Modifier.fillMaxSize(),
                        data = state.value.privileges ?: listOf(),
                        loadingState = state.value.privilegesLoadingState,
                        icon = R.drawable.ic_privileges,
                        title = "Льготы и выплаты"
                    )
                }
            }
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
                        .progress { progress.floatValue = it }
                        .alpha(progress.floatValue)
                        .padding(top = 70.dp)
                        .scale(0.8f + scale)
                        .offset(y = (-10).dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (state.value.cards?.size != 0) {
                        HorizontalPager(
                            state = pagerState,
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            pageSpacing = 16.dp
                        ) {
                            DetailCardInfo(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp),
                            ) {
                                state.value.cards?.get(0)?.let {
                                    viewModel.navigateToDetailCard(cardId = it.key)
                                }
                            }
                        }
                        PageIndicator(
                            currentPage = pagerState.currentPage,
                            count = state.value.cards?.size ?: 1
                        )
                    }
                }
                TextButton(
                    modifier = Modifier
                        .pin()
                        .height(60.dp)
                        .padding(horizontal = 16.dp),
                    onClick = { viewModel.navigateToProfile() }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(35.dp),
                        painter = painterResource(id = drawable.ic_user_circle),
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Егор Лядский",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
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
                item {
                    ServiceItemView(
                        Modifier.padding(horizontal = 16.dp),
                        title = "Медицина",
                        icon = R.drawable.ic_medical
                    ) {
                        viewModel.onSelectServiceClick(Services.Medical)
                        scope.launch {
                            modalBottomSheetState.show()
                        }
                    }
                }
                item {
                    ServiceItemView(
                        Modifier.padding(horizontal = 16.dp).padding(top = 10.dp),
                        title = "Льготы и выплаты",
                        icon = R.drawable.ic_privileges
                    ) {
                        viewModel.onSelectServiceClick(Services.Privileges)
                        viewModel.getPrivileges()
                        scope.launch {
                            modalBottomSheetState.show()
                        }
                    }
                }
            }
        }
    }
}
