package ui.bottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.centr_invest_card_client.components.bottomNavigation.BottomNavigationComponent
import com.lyadsky.centr_invest_card_client.ui.bottomNavigation.BottomBar
import com.lyadsky.centr_invest_card_client.ui.bottomNavigation.BottomNavigationChildren

@Composable
fun BottomNavigationScreen(component: BottomNavigationComponent) {

    Column (Modifier.fillMaxSize()) {
        BottomNavigationChildren(component = component, modifier = Modifier.weight(1f))
        BottomBar(component = component, modifier = Modifier.fillMaxWidth())
    }
}
