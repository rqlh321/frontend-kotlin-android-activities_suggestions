package com.example.feature_auth

import ru.gubatenko.mvi.Reducer

class OfferAuthReducer : Reducer<OfferAuthStore.State, OfferAuthStore.SideAction> {
    override fun invoke(
        currentState: OfferAuthStore.State,
        newAction: OfferAuthStore.SideAction
    ) = currentState
}