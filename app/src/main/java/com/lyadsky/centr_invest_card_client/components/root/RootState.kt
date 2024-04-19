package components.root

import utils.LoadingState

data class RootState(

    val categoriesLoadingState: LoadingState = LoadingState.Loading
)