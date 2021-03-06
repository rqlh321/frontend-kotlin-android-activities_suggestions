package com.example.feature_mainframe

import ru.gubatenko.mvi.Reducer

class MainframeReducer : Reducer<MainframeStore.State, MainframeStore.SideAction> {
    override fun invoke(
        currentState: MainframeStore.State,
        newAction: MainframeStore.SideAction
    ): MainframeStore.State = currentState
}
