package ru.gubatenko.core

abstract class AbstractStore<Action : Any, SideAction : Any, State : Any>(
    initialState: State,
    stateObservableFactory: StateObservable.Factory,
    private val logger: Logger? = null,
    private val reducer: Reducer<State, SideAction>,
    private val sideEffects: SideEffects<Action, SideAction>,
) : Store<Action> {

    val state: StateObservable<State> = stateObservableFactory.create(initialState)

    override suspend fun process(action: Action) {
        logger?.log("process action: $action")
        sideEffects.execute(action, ::produceNextAction)
    }

    private fun produceNextAction(sideAction: SideAction) {
        logger?.log("current state: ${state.stateValue}; sideAction: $sideAction")
        val newState = reducer(state.stateValue, sideAction)
        state.stateValue = newState
        logger?.log("new state: $newState")
    }
}
