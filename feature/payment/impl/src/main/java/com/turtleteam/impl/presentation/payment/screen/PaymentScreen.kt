package com.turtleteam.impl.presentation.payment.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.model.PaymentType
import com.turtleteam.core_view.R
import com.turtleteam.core_view.models.Operation
import com.turtleteam.core_view.view.bottomSheet.OperationBottomSheet
import com.turtleteam.impl.presentation.payment.screen.layout.BetweenLayout
import com.turtleteam.impl.presentation.payment.screen.layout.NumberBillLayout
import com.turtleteam.impl.presentation.payment.screen.layout.NumberCardLayout
import com.turtleteam.impl.presentation.payment.screen.layout.PhoneLayout
import com.turtleteam.impl.presentation.payment.screen.layout.SbpLayout
import com.turtleteam.impl.presentation.payment.viewModel.PaymentViewModel

@Composable
fun PaymentScreen(paymentType: String, viewModel: PaymentViewModel) {
    val showBottomSheet = remember { mutableStateOf(false) }
    val state = viewModel.state.collectAsState()
    OperationBottomSheet(
        operation = Operation(
            id = "1",
            sum = state.value.sumText,
            date = "20.11.2023",
            bankRecipient = state.value.selectBank?: "",
            billRecipient = "1234 1231 8789 3432",
            recipientType = "Дебетовый счет",
            phoneRecipient = state.value.phoneVariantText,
            status = "Выполнен",
            operationType = "Перевод",
            operationCategory = state.value.selectCategory?:"",
            numberReceipt = "1234 1231 8789 3432",
            commission = "00%",
            comment = state.value.commentText
        ), showBottomSheet = showBottomSheet
    ){
        viewModel.onTransactionClick()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { viewModel.onBackButtonClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back), contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Перевести", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ), modifier = Modifier.padding(start = 20.dp)
            )
        }

        when (paymentType) {
            PaymentType.PHONE.name -> {
                PhoneLayout(viewModel){ showBottomSheet.value = true }
            }

            PaymentType.NUMBER_CARD.name -> {
                NumberCardLayout(viewModel){ showBottomSheet.value = true }
            }

            PaymentType.NUMBER_BILL.name -> {
                NumberBillLayout(viewModel){ showBottomSheet.value = true }
            }

            PaymentType.SBP.name -> {
                SbpLayout(viewModel){ showBottomSheet.value = true }
            }

            PaymentType.BETWEEN_BILL.name -> {
                BetweenLayout()
            }

        }
    }
}