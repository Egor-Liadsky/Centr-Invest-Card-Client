package com.lyadsky.centr_invest_card_client.ui.views.snackbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                backgroundColor = Color.White,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {

                        Text(
                            text = it.message, style = TextStyle(
                                color = Color.Black,
                                fontSize = 15.sp,
                            ), modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}