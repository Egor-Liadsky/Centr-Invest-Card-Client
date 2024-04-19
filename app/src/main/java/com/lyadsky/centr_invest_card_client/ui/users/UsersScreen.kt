package ui.users

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.users.UsersComponent

@Composable
fun UsersScreen(component: UsersComponent) {

    val state by component.viewStates.subscribeAsState()

    Text("Users Screen")
}