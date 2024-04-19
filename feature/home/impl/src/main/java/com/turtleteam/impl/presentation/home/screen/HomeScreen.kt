package com.turtleteam.impl.presentation.home.screen

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.model.PaymentType
import com.turtleteam.core_view.R.drawable
import com.turtleteam.core_view.models.Operation
import com.turtleteam.core_view.view.PageIndicator
import com.turtleteam.core_view.view.bottomSheet.OperationBottomSheet
import com.turtleteam.core_view.view.cards.DetailCardInfo
import com.turtleteam.core_view.view.layout.OperationView
import com.turtleteam.impl.presentation.home.screen.component.SmallCardView
import com.turtleteam.impl.presentation.home.screen.component.cardList
import com.turtleteam.impl.presentation.home.viewModel.HomeViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

data class PaymentVariant(
    val label: String,
    @DrawableRes
    val icon: Int,
    val paymentType: PaymentType
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val progress = remember { mutableFloatStateOf(0f) }
    val showBottomSheet = remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsState()
    val pagerState = rememberPagerState { state.value.cards?.size ?: 1 }

    val paymentVariantList = listOf(
        PaymentVariant(
            label = "По номеру телефона",
            icon = drawable.ic_phone,
            paymentType = PaymentType.PHONE
        ),
        PaymentVariant(
            label = "По номеру карты",
            icon = drawable.ic_card_pay,
            paymentType = PaymentType.NUMBER_CARD
        ),
        PaymentVariant(
            label = "По номеру счета",
            icon = drawable.ic_ruble,
            paymentType = PaymentType.NUMBER_BILL
        ),
        PaymentVariant(
            label = "Перевод через СБП",
            icon = drawable.ic_sbp,
            paymentType = PaymentType.SBP
        ),
        PaymentVariant(
            label = "Между счетами",
            icon = drawable.ic_between_bill,
            paymentType = PaymentType.BETWEEN_BILL
        )
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

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .clipToBounds(),
        toolbarModifier = Modifier.background(
            Color(0xFFE8F5E9),
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
                if (state.value.cards?.size != 0){
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
                            name = "",
                            cardNumber = state.value.cards?.get(it)?.number ?: "",
                            date = state.value.cards?.get(it)?.dateClose ?: ""
                        ) {
                            viewModel.navigateToDetailCard(cardList[it].numCards)
                        }
                    }
                    PageIndicator(
                        currentPage = pagerState.currentPage, count = state.value.cards?.size ?: 1
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
                    tint = Color(0xFFA5D6A7)
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "${state.value.user?.user?.firstname} ${state.value.user?.user?.lastname}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "1000 ₽",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color.Black
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
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Переводы",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                )
            }
            item {
                LazyRow(
                    modifier = Modifier.padding(top = 20.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = paymentVariantList) { paymentVariant ->
                        SmallCardView(
                            icon = paymentVariant.icon,
                            text = paymentVariant.label,
                        ) {
                            viewModel.navigateToPayment(paymentVariant.paymentType)
                        }
                    }
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
                OperationView {
                    showBottomSheet.value = true
                }
            }
        }
    }
}
