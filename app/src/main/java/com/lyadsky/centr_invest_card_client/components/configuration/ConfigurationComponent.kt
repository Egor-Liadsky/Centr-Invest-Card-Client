package components.configuration

import com.arkivanov.decompose.value.Value

interface ConfigurationComponent {

    val viewStates: Value<ConfigurationState>
}