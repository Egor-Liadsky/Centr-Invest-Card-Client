package com.turtleteam.impl.presentation.payment.screen.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.view.dropdown.SelectDropView
import com.turtleteam.core_view.view.textfields.CustomTextField
import com.turtleteam.impl.presentation.payment.viewModel.PaymentViewModel

@Composable
fun NumberCardLayout(viewModel: PaymentViewModel, transactionClick: () -> Unit) {
    val state = viewModel.state.collectAsState()
    var expandedBank by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }

    val bankList =
        listOf("Центр-Инвест", "Альфа-банк", "Сбербанк", "Втб", "Совкомбанк", "Промсвязьбанк")

    val categoryList = listOf("Все", "Услуги", "Книги", "Магазины", "Кредит")


    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            Text(
                text = "1. Введи номер карты того, кому хочешь перевести деньги:",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            CustomTextField(
                Modifier.padding(top = 16.dp),
                icon = R.drawable.ic_card_pay,
                value = state.value.phoneVariantText,
                onValueChange = { viewModel.phoneVariantTextChanged(it) },
                placeholder = "Введите номер карты",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
            )

            Text(
                text = "2. Выберите банк получателя:", modifier = Modifier.padding(top = 30.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Column {
                Column(Modifier.padding(top = 16.dp)) {
                    SelectDropView(
                        icon = R.drawable.ic_bank,
                        iconColor = Color(0xFF00602A),
                        textColor = if (state.value.selectBank == null) Color(0x4D1F1F1F) else Color.Black,
                        placeholder = if (state.value.selectBank == null) "Выберите банк" else state.value.selectBank.toString()
                    ) {
                        expandedBank = true
                    }
                }

                DropdownMenu(expanded = expandedBank, onDismissRequest = { expandedBank = false }) {
                    bankList.forEach { bank ->
                        DropdownMenuItem(text = {
                            Text(text = bank)
                        }, onClick = {
                            viewModel.onClickSelectBank(bank)
                            expandedBank = false
                        })
                    }
                }
            }

            Text(
                text = "3. Выберите категорию перевода:", modifier = Modifier.padding(top = 30.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Column {
                Column(Modifier.padding(top = 16.dp)) {
                    SelectDropView(
                        icon = R.drawable.ic_category,
                        iconColor = Color(0xFF00602A),
                        textColor = if (state.value.selectCategory == null) Color(0x4D1F1F1F) else Color.Black,
                        placeholder = if (state.value.selectCategory == null) "Выберите категорию" else state.value.selectCategory.toString()
                    ) {
                        expandedCategory = true
                    }
                }

                DropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false }) {
                    categoryList.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(text = category)
                            },
                            onClick = {
                                viewModel.onClickSelectCategory(category)
                                expandedCategory = false
                            })
                    }
                }
            }

            Text(
                text = "4. Если вы хотите что либо сообщить, можете написать комментарий:",
                modifier = Modifier.padding(top = 30.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            CustomTextField(
                Modifier.padding(top = 16.dp),
                icon = R.drawable.ic_comment,
                value = state.value.commentText,
                onValueChange = { viewModel.commentTextChanged(it) },
                placeholder = "Введите комментарий",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
            )

            Text(
                text = "5. Введи сумму перевода:", modifier = Modifier.padding(top = 30.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            CustomTextField(
                Modifier.padding(top = 16.dp),
                icon = R.drawable.ic_ruble,
                value = state.value.sumText,
                onValueChange = { viewModel.sumTextChanged(it) },
                placeholder = "100 ₽",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF049C6B)),
                onClick = { transactionClick() }
            ) {
                Text(text = "Перевести", modifier = Modifier.padding(vertical = 10.dp))
            }
        }
    }
}