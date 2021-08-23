package ru.gubatenko.mvi

abstract class AbstractStore<Action : Any, SideAction : Any, State : Any>(
    val stateObservable: StateObservable<State>,
    private val logger: Logger? = null,
    private val reducer: Reducer<State, SideAction>,
    private val sideEffects: SideEffects<Action, SideAction>,
) : Store<Action> {

    override suspend fun process(action: Action) {
        logger?.log("process action: $action")
        sideEffects.execute(action, ::produceNextAction)
    }

    private fun produceNextAction(sideAction: SideAction) {
        logger?.log("current state: ${stateObservable.stateValue}; sideAction: $sideAction")
        val newState = reducer(stateObservable.stateValue, sideAction)
        stateObservable.stateValue = newState
        logger?.log("new state: $newState")
    }
}
