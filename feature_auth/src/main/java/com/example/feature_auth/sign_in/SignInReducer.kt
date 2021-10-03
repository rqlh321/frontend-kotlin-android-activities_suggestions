package com.example.feature_auth.sign_in

import ru.gubatenko.mvi.Reducer

class SignInReducer : Reducer<SignInStore.State, SignInStore.SideAction> {
    override fun invoke(
        currentState: SignInStore.State,
        newAction: SignInStore.SideAction
    ) = currentState
}