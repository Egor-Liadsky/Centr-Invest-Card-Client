package ui.programs

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.programs.ProgramsComponent

@Composable
fun ProgramsScreen(component: ProgramsComponent) {

    val state by component.viewStates.subscribeAsState()

    Text("Programs Screen")
}
