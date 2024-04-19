package components.bottomNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import components.bottomNavigation.BottomNavigationComponent.Child
import components.configuration.ConfigurationComponentImpl
import components.programs.ProgramsComponentImpl
import components.users.UsersComponentImpl
import kotlinx.serialization.Serializable

class BottomNavigationComponentComponentImpl(
    componentContext: ComponentContext,
) : BottomNavigationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Programs,
            childFactory = ::childFactory
        )

    override fun onTabClicked(tab: MainNavTab) {
        when (tab) {
            MainNavTab.PROGRAMS -> navigation.bringToFront(Config.Programs)
            MainNavTab.USERS -> navigation.bringToFront(Config.Users)
            MainNavTab.CONFIGURATION -> navigation.bringToFront(Config.Configuration)
        }
    }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    private fun childFactory(
        config: Config,
        componentContext: ComponentContext
    ): Child {
        return when (config) {
            Config.Programs -> programsComponent(componentContext)
            Config.Configuration -> configurationComponent(componentContext)
            Config.Users -> usersComponent(componentContext)
        }
    }

    private fun programsComponent(componentContext: ComponentContext): BottomNavigationComponent.Child =
        BottomNavigationComponent.Child.ProgramsChild(
            ProgramsComponentImpl(componentContext = componentContext)
        )

    private fun usersComponent(componentContext: ComponentContext): BottomNavigationComponent.Child =
        BottomNavigationComponent.Child.UsersChild(
            UsersComponentImpl(componentContext = componentContext)
        )

    private fun configurationComponent(componentContext: ComponentContext): BottomNavigationComponent.Child =
        BottomNavigationComponent.Child.ConfigurationChild(
            ConfigurationComponentImpl(componentContext = componentContext)
        )

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Programs : Config

        @Serializable
        data object Users : Config

        @Serializable
        data object Configuration : Config
    }
}