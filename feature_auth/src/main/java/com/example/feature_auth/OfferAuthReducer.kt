package com.example.feature_auth

import ru.gubatenko.mvi.Reducer

class OfferAuthReducer : Reducer<OfferAuthStore.State, OfferAuthStore.SideAction> {
    override fun invoke(
        currentState: OfferAuthStore.State,
        newAction: OfferAuthStore.SideAction
    ) = when (newAction) {
        is OfferAuthStore.SideAction.SetupAuthOfferScreen -> currentState.copy(
            titleText = newAction.labelText,
            acceptTextButton = newAction.buttonText,
        )
    }
}