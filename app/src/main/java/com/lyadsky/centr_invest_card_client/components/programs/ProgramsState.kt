package components.programs

import utils.LoadingState

data class ProgramsState(
    val productsLoadingState: LoadingState = LoadingState.Loading,
)