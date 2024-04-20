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
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

data class Service(
    val title: String,
    @DrawableRes
    val icon: Int,
)

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
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

    val services = listOf(
        Service(
            title = "Медицина",
            icon = R.drawable.ic_medical
        ),
        Service(
            title = "Транспорт",
            icon = R.drawable.ic_transport
        ),
        Service(
            title = "Льготы и выплаты",
            icon = R.drawable.ic_privileges
        ),
    )

    OperationBottomSheet(
        operation = Operation(
            id = "1",
            sum = "85",
            date = "20.11.2023",
            bankRecipient = "Центр-Инвест",
            billRecipient = "1234 1231 8789 3432",
            recipientType = "Дебетовый счет",
            phoneRecipient = "+79044422123",
            status = "Выполнен",
            operationType = "Перевод",
            operationCategory = "Книги",
            numberReceipt = "1234 1231 8789 3432",
            commission = "20%",
            comment = "на еду"
        ), showBottomSheet = showBottomSheet
    )

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {


            HistorySheetLayout(modifier = Modifier.fillMaxSize())
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
                Row(
                    modifier = Modifier
                        .pin()
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
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
                items(items = services) { service ->
                    ServiceItemView(
                        Modifier.padding(horizontal = 16.dp),
                        title = service.title,
                        icon = service.icon
                    ) {
                        scope.launch { modalBottomSheetState.show() }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }
                item {
                    ServiceItemView(
                        Modifier.padding(horizontal = 16.dp),
                        title = "Льготы и выплаты",
                        icon = R.drawable.ic_privileges
                    ) {
                        scope.launch { modalBottomSheetState.show() }
                    }
                }
            }
        }
    }
}
