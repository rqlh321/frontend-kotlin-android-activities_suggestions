package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.AcceptedActivitiesStore
import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class ActivitiesViewModel(
    private val store: AcceptedActivitiesStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)

    init {
        io {
            store.process(AcceptedActivitiesStore.Action.LoadContent)
        }
    }
}