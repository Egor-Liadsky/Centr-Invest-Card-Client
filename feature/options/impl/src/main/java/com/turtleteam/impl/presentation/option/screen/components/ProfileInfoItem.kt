package com.turtleteam.impl.presentation.option.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProfileCategoryItem(
    val title: String,
    val placeholder: String,
    val onClick: () -> Unit
)

@Composable
fun ProfileInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    profileCategoryItem: List<ProfileCategoryItem>
) {
    Column(
        modifier
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, Color(0xFFF1F3F5), RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
    ) {
//        Column(Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
//            Text(
//                text = title, style = TextStyle(
//                    fontFamily = fontQanelas,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 20.sp,
//                    color = colors.black
//                ), modifier = Modifier.padding(bottom = 4.dp)
//            )
//
//            profileCategoryItem.forEach { item ->
//                ProfileCategoryButton(
//                    profileCategoryItem = item
//                )
//            }
//        }
    }
}
