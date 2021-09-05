package com.example.feature_accepted_activities.side_effects

import com.example.feature_accepted_activities.AcceptedActivitiesStore
import ru.gubatenko.domain.usecase.GetAllSavedActivitiesUseCase
import ru.gubatenko.mvi.SideEffect

class ShowContentSideEffect(
    val useCase: GetAllSavedActivitiesUseCase
) : SideEffect<AcceptedActivitiesStore.Action.LoadContent, AcceptedActivitiesStore.SideAction> {

    override fun actionId() = AcceptedActivitiesStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: AcceptedActivitiesStore.Action.LoadContent,
        reducerCallback: suspend (AcceptedActivitiesStore.SideAction) -> Unit
    ) {
        val activities = useCase.execute()
        reducerCallback.invoke(AcceptedActivitiesStore.SideAction.LoadSuccess(activities))
    }
}
