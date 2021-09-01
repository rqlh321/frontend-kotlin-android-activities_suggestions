package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.SideEffect
import ru.gubatenko.mvi.StateObservable

class ClickOnSaveSideEffect(
    private val state: StateObservable<MainStore.State>,
    private val useCase: SaveActivityToLocalStorageUseCase,
) : SideEffect<MainStore.Action.SaveContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.SaveContent::class.java

    override suspend fun execute(
        action: MainStore.Action.SaveContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        val actionToSave = state.stateValue.action ?: return
        useCase.execute(actionToSave)
    }
}
