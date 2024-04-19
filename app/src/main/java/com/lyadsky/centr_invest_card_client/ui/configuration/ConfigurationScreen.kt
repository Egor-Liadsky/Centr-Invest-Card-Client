package ui.configuration

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.configuration.ConfigurationComponent

@Composable
fun ConfigurationScreen(component: ConfigurationComponent) {

    val state by component.viewStates.subscribeAsState()

    Text("Configuration Screen")
}