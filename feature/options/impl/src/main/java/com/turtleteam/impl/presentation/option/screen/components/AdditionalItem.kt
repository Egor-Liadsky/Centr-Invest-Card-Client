import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.impl.R
import com.turtleteam.impl.presentation.option.viewModel.OptionsViewModel

data class AdditionalInfo(
    val title: String,
    val onClick: () -> Unit
)

@Composable
fun AdditionalView(viewModel: OptionsViewModel) {
    val list = listOf(
        AdditionalInfo(title = "Обратная связь", onClick = { viewModel.navigateToFeedback() }),
        AdditionalInfo(title = "FAQ", onClick = { viewModel.navigateToFaq()  }),
        AdditionalInfo(title = "О приложении", onClick = { viewModel.navigateToAboutApp() }),
    )

    Column(
        Modifier
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            .border(1.dp, Color(0xFFF1F3F5), RoundedCornerShape(5.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 25.dp)
        ) {
            Text(
                text = "Дополнительно", style = TextStyle(
                    fontFamily = FontFamily(Font(com.turtleteam.core_view.R.font.qanelas)),
                    fontSize = 20.sp,
                    color = Color(0xFF2A2F33)
                ), modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
            )
            list.forEach {
                AdditionalItem(additionalInfo = it)
            }
        }
    }
}

@Composable
fun AdditionalItem(additionalInfo: AdditionalInfo) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                additionalInfo.onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = com.turtleteam.core_view.R.drawable.ic_square),
                contentDescription = "ic_square",
                modifier = Modifier.size(width = 18.dp, height = 20.dp),
                tint = Color(0xFFA8ACAF)
            )
            Text(
                text = additionalInfo.title, style = TextStyle(
                    fontFamily = FontFamily(Font(com.turtleteam.core_view.R.font.qanelas)),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF2A2F33)
                ), modifier = Modifier.padding(start = 18.dp)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = com.turtleteam.core_view.R.drawable.ic_arrow_right), contentDescription = "ic_next",
                tint = Color(0xFF2A2F33)
            )
        }
    }
}
