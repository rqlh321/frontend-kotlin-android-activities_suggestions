package com.example.feature_auth

import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import com.example.audit.Logger

class OfferAuthStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<OfferAuthStore.Action, OfferAuthStore.SideAction, OfferAuthStore.State>(
    logger = logger,
    stateObservable = stateObservable,
    reducer = OfferAuthReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object FetchAuthScreenData : Action()
        object AcceptAuthOffer : Action()
        object RejectAuthOffer : Action()
    }

    sealed class SideAction {
        data class SetupAuthOfferScreen(
            val labelText: String,
            val buttonText: String,
        ) : SideAction()
    }

    sealed class Event {
        object NavigateToAuthorization : Event()
    }

    data class State(
        val titleText: String? = null,
        val acceptTextButton: String? = null
    )
}