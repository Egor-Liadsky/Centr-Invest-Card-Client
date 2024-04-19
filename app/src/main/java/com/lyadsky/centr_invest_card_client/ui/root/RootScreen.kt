package ui.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.root.RootComponent
import ui.bottomNavigation.BottomNavigationScreen

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.viewStates.subscribeAsState()

    Column(
        Modifier.fillMaxSize()
    ) {

        Children(
            stack = component.childStack,
            modifier = modifier,
            animation = stackAnimation(fade())
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.BottomNavigationChild -> BottomNavigationScreen(child.component)
            }
        }
    }
}