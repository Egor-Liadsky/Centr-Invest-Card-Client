package ui.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import components.bottomNavigation.BottomNavigationComponent
import ui.configuration.ConfigurationScreen
import ui.programs.ProgramsScreen
import ui.users.UsersScreen

@Composable
fun BottomNavigationChildren(
    component: BottomNavigationComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is BottomNavigationComponent.Child.ConfigurationChild -> ConfigurationScreen(child.component)
            is BottomNavigationComponent.Child.ProgramsChild -> ProgramsScreen(child.component)
            is BottomNavigationComponent.Child.UsersChild -> UsersScreen(child.component)
        }
    }
}