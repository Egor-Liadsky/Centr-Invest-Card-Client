package components.programs

import com.arkivanov.decompose.ComponentContext
import components.BaseComponent
import org.koin.core.component.KoinComponent

class ProgramsComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent<ProgramsState>(componentContext, ProgramsState()), ProgramsComponent, KoinComponent {

}