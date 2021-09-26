package com.example.feature_auth.sign_in

import com.example.audit.Logger
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class SignInStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<SignInStore.Action, SignInStore.SideAction, SignInStore.State>(
    logger = logger,
    stateObservable = stateObservable,
    reducer = SignInReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        data class SignIn(val credential: Any) : Action()
    }

    sealed class SideAction

    sealed class Event {
        object SignInSuccess : Event()
        object SignInFail : Event()
    }

    class State
}