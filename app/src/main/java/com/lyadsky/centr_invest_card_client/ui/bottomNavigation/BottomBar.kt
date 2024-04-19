package ui.bottomNavigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.ui.theme.Color
import components.bottomNavigation.BottomNavigationComponent
import components.bottomNavigation.BottomNavigationComponent.Child
import components.bottomNavigation.MainNavTab
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import redoskiosk.composeapp.generated.resources.Res
import redoskiosk.composeapp.generated.resources.ic_configuration
import redoskiosk.composeapp.generated.resources.ic_programs
import redoskiosk.composeapp.generated.resources.ic_users

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBar(component: BottomNavigationComponent, modifier: Modifier = Modifier) {

    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    Divider(
        Modifier
            .fillMaxWidth()
            .height(1.dp),
        color = Color.BottomBar.stroke
    )

    BottomNavigation(
        modifier = modifier.height(60.dp),
        backgroundColor = Color.BottomBar.background,
        elevation = 0.dp,
    ) {

        BottomNavigationItem(
            selected = activeComponent is Child.ProgramsChild,
            onClick = { component.onTabClicked(MainNavTab.PROGRAMS) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_programs),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text("Программы")
            }
        )
        BottomNavigationItem(
            selected = activeComponent is Child.UsersChild,
            onClick = { component.onTabClicked(MainNavTab.USERS) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_users),
                    contentDescription = "Пользователи",
                    Modifier.size(24.dp)
                )
            },
            label = {
                Text("Пользователи")
            }
        )
        BottomNavigationItem(
            selected = activeComponent is Child.ConfigurationChild,
            onClick = { component.onTabClicked(MainNavTab.CONFIGURATION) },
            selectedContentColor = Color.BottomBar.selectedNavigationItem,
            unselectedContentColor = Color.BottomBar.unselectedNavigationItem,
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_configuration),
                    contentDescription = "Конфигурация",
                    Modifier.size(24.dp)
                )
            },
            label = {
                Text("Конфигурация")
            }
        )
    }
}