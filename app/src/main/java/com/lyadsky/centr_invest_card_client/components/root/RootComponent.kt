package components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import components.bottomNavigation.BottomNavigationComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    val viewStates: Value<RootState>

    fun onBackButtonClick()

    sealed class Child {
        data class BottomNavigationChild(val component: BottomNavigationComponent) : Child()
    }
}