package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.PromiseStore
import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class PromiseViewModel(
    private val store: PromiseStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)

    init {
        unconfined {
            store.process(PromiseStore.Action.LoadPromises)
        }
    }
}