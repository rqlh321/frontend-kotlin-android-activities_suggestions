package com.example.feature_accepted_activities

import ru.gubatenko.mvi.Reducer

class AcceptedActivitiesStoreReducer :
    Reducer<AcceptedActivitiesStore.State, AcceptedActivitiesStore.SideAction> {
    override fun invoke(
        currentState: AcceptedActivitiesStore.State,
        newAction: AcceptedActivitiesStore.SideAction
    ): AcceptedActivitiesStore.State = when (newAction) {

        is AcceptedActivitiesStore.SideAction.LoadSuccess -> currentState.copy(
            activities = newAction.activities
        )
    }
}
