package components.configuration

import com.arkivanov.decompose.ComponentContext
import components.BaseComponent

class ConfigurationComponentImpl(
    componentContext: ComponentContext
) : BaseComponent<ConfigurationState>(componentContext, ConfigurationState()),
    ConfigurationComponent {

}