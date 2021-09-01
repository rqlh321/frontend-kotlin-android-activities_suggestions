package com.example.feature_auth

import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class OfferAuthStore(
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<OfferAuthStore.Action, OfferAuthStore.SideAction, OfferAuthStore.State>(
    stateObservable = stateObservable,
    reducer = OfferAuthReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object AcceptAuthOffer : Action()
        object RejectAuthOffer : Action()
    }

    sealed class SideAction

    sealed class Event {
        object NavigateToAuthorization : Event()
    }

    data class State(
        val titleText: String,
        val acceptTextButton: String
    )
}