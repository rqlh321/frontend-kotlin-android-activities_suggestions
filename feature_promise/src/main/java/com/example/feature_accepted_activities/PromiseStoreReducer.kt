package com.example.feature_accepted_activities

import ru.gubatenko.mvi.Reducer

class PromiseStoreReducer :
    Reducer<PromiseStore.State, PromiseStore.SideAction> {
    override fun invoke(
        currentState: PromiseStore.State,
        newAction: PromiseStore.SideAction
    ): PromiseStore.State = when (newAction) {

        is PromiseStore.SideAction.LoadSuccess -> currentState.copy(
            promiseList = newAction.promiseList,
            isPromiseListVisible = newAction.isPromiseListVisible,
            infoText = newAction.infoText,
            isInfoTextVisible = newAction.isInfoTextVisible,
        )
    }
}
