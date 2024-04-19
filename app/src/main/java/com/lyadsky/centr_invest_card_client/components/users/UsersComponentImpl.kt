package components.users

import com.arkivanov.decompose.ComponentContext
import components.BaseComponent

class UsersComponentImpl(
    componentContext: ComponentContext
) : BaseComponent<UsersState>(componentContext, UsersState()), UsersComponent {

}