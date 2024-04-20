package com.turtleteam.core_view.view.snackbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R

@Composable
fun DefaultSnackbar(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xFF2A2F33), RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_snackbar_warning),
                            contentDescription = "ic_snackbar_info",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = it.message, style = TextStyle(
                                color = Color(0xFF2A2F33),
                                fontFamily = FontFamily(Font(R.font.qanelas)),
                                fontSize = 15.sp,
                            ), modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                }
            )
        },
    )
}
