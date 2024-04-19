package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import org.jetbrains.compose.resources.ExperimentalResourceApi
import redoskiosk.composeapp.generated.resources.Res
import redoskiosk.composeapp.generated.resources.despair_display_bold
import redoskiosk.composeapp.generated.resources.gilroy_bold
import redoskiosk.composeapp.generated.resources.gilroy_semibold

object Fonts {

    object Gilroy {
        @Composable
        @OptIn(ExperimentalResourceApi::class)
        fun bold(): FontFamily = org.jetbrains.compose.resources.Font(
            Res.font.gilroy_bold,
            FontWeight.Bold
        ).toFontFamily()

        @Composable
        @OptIn(ExperimentalResourceApi::class)
        fun semibold(): FontFamily = org.jetbrains.compose.resources.Font(
            Res.font.gilroy_semibold,
            FontWeight.SemiBold
        ).toFontFamily()
    }

    object DespairDisplay {
        @Composable
        @OptIn(ExperimentalResourceApi::class)
        fun bold(): FontFamily = org.jetbrains.compose.resources.Font(
            Res.font.despair_display_bold,
            FontWeight.Bold
        ).toFontFamily()
    }
}