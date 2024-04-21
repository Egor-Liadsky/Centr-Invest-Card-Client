package com.turtleteam.impl.presentation.home.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.api.model.FullPrivileges
import com.turtleteam.api.api.model.Service
import com.turtleteam.core_navigation.state.LoadingState
import com.turtleteam.core_view.R
import com.turtleteam.core_view.models.ServiceHistory
import com.turtleteam.core_view.view.cards.ServiceHistoryItemView

@Composable
fun HistorySheetLayout(
    modifier: Modifier,
    data: List<Service>,
    loadingState: LoadingState,
    title: String,
    icon: Int
) {

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Divider(
            Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(2.dp))
                .width(22.dp), color = Color(0xFF9E9C9F), thickness = 2.dp
        )

        LazyColumn {
            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 20.dp, top = 30.dp),
                    text = title,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.qanelas)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF2A2F33)
                    ),
                )
            }

            if (loadingState == LoadingState.Loading) {
                item {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            Modifier.size(24.dp),
                            color = Color(0xFF04659C)
                        )
                    }
                }
            } else {
                items(items = data) { service ->
                    ServiceHistoryItemView(
                        service = ServiceHistory(
                            date = service.dateTime,
                            price = service.price,
                            reason = service.reason,
                            inn = service.inn,
                            category_id = service.category_id
                        )
                    )
                }
            }
        }
    }
}
