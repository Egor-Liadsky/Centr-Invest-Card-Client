package com.turtleteam.core_view.view.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.models.Operation
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationBottomSheet(operation: Operation, showBottomSheet: MutableState<Boolean>, onHide:() -> Unit = {}) {
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                onHide()
                showBottomSheet.value = false
            },
            sheetState = sheetState
        ) {
            Column(
                Modifier.fillMaxSize(),
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = operation.date, modifier = Modifier.padding(top = 16.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_turtle),
                        contentDescription = null,
                        modifier = Modifier
                            .size(129.dp)
                            .padding(top = 24.dp)
                    )

                    Text(
                        text = operation.status, modifier = Modifier.padding(top = 24.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Text(
                        text = operation.operationType, modifier = Modifier.padding(top = 24.dp),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )

                    Text(
                        text = "+ ${operation.sum}", modifier = Modifier.padding(top = 24.dp),
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )

                    Text(
                        text = operation.comment,
                        modifier = Modifier
                            .padding(top = 35.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFEFEFEF))
                            .padding(horizontal = 70.dp, vertical = 14.dp)
                    )

                    Row(Modifier.padding(top = 24.dp)) {
                        RefillBill(
                            bankName = operation.bankRecipient,
                            numberCode = operation.numberReceipt
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RefillBill(bankName: String, numberCode: String) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Column(Modifier.padding(horizontal = 10.dp, vertical = 16.dp)) {
            Text(
                text = "Пополнение счета", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            Row(
                Modifier.padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_card), contentDescription = null,
                    modifier = Modifier.size(width = 44.dp, height = 28.dp)
                )
                Spacer(modifier = Modifier.padding(end = 16.dp))
                Column {
                    Text(
                        text = bankName, style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.End
                        )
                    )
                    Text(
                        text = numberCode, style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.padding(top = 7.dp)
                    )
                }
            }
        }
    }
}
