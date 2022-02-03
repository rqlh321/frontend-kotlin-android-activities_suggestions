package com.example.feature_frame_android

import com.example.feature_mainframe.MainframeStore
import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class MainframeViewModel(
    dispatcher: EventDispatcher<MainframeStore.Event>,
    store: MainframeStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)
    val event = (dispatcher as LiveDataEventDispatcher<MainframeStore.Event>)

    init {
//         main {
//             store.process(MainframeStore.Action.SetupScreen)
//         }
    }
}
