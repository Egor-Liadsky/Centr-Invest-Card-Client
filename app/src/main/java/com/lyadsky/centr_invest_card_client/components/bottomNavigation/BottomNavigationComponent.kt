package components.bottomNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import components.configuration.ConfigurationComponent
import components.programs.ProgramsComponent
import components.users.UsersComponent

interface BottomNavigationComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onTabClicked(tab: MainNavTab)

    fun onBackClicked(toIndex: Int)

    sealed class Child {

        data class ProgramsChild(val component: ProgramsComponent): Child()
        data class UsersChild(val component: UsersComponent): Child()
        data class ConfigurationChild(val component: ConfigurationComponent): Child()
    }
}

enum class MainNavTab {
    PROGRAMS,
    USERS,
    CONFIGURATION
}