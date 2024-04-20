package com.turtleteam.impl.presentation.pincode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.view.pincode.PincodeIndicators
import com.turtleteam.core_view.view.pincode.PincodeKeyboard
import com.turtleteam.impl.presentation.pincode.viewmodel.PINCODE_LENGTH
import com.turtleteam.impl.presentation.pincode.viewmodel.PincodeViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun PincodeScreen(
    viewModel: PincodeViewModel
) {
    val state = viewModel.state.collectAsState()
    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF04659C))
            .clipToBounds(),
        toolbarModifier = Modifier.background(Color(0xFF04659C)),
        state = rememberCollapsingToolbarScaffoldState(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFF04659C))
                    .pin(),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            tint = Color.Transparent,
                            painter = painterResource(id = com.turtleteam.core_view.R.drawable.ic_back),
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier.padding(bottom = 30.dp, start = 16.dp),
                        text = if (state.value.pincodeIsNull) "Установка ПИН-кода" else "Введите ПИН-код",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.value.pincodeIsNull)
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 16.dp),
                    text = "Придумай пин-код для входа в приложение",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),

                        )
                )
            Spacer(modifier = Modifier.weight(1f))
            PincodeIndicators(pincode = state.value.pincode.length, length = PINCODE_LENGTH)
            Spacer(modifier = Modifier.weight(1f))
            PincodeKeyboard(
                onClick = { viewModel.onPincodeTextChanged(it) },
                onDeleteClick = { viewModel.onBackspaceClick() },
                onDoneClick = if (state.value.saveBtnEnabled) {
                    { viewModel.onDoneClick() }
                } else null)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}