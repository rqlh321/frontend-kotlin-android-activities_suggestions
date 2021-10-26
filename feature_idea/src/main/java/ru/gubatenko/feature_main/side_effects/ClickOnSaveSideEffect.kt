package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.mvi.SideEffect
import ru.gubatenko.mvi.StateObservable

class ClickOnSaveSideEffect(
    private val stateObservable: StateObservable<IdeaStore.State>,
    private val saveActivityToLocalStorageUseCase: SaveActivityToLocalStorageUseCase,
) : SideEffect<IdeaStore.Action.SaveContent, IdeaStore.SideAction> {

    override fun actionId() = IdeaStore.Action.SaveContent::class.java

    override suspend fun execute(
        action: IdeaStore.Action.SaveContent,
        reducerCallback: suspend (IdeaStore.SideAction) -> Unit
    ) {
        val actionToSave = stateObservable.stateValue.idea ?: throw IllegalArgumentException()
        saveActivityToLocalStorageUseCase.execute(actionToSave)
    }
}
