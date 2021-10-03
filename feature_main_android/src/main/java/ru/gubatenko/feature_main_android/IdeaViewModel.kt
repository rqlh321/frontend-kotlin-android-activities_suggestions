package ru.gubatenko.feature_main_android

import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class IdeaViewModel(
    dispatcher: EventDispatcher<MainStore.Event>,
    private val store: MainStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)
    val event = (dispatcher as LiveDataEventDispatcher<MainStore.Event>)

    init {
        io {
            store.process(MainStore.Action.LoadContent)
        }
    }

    fun reload() = io {
        store.process(MainStore.Action.LoadContent)
    }

    fun save() = io {
        store.process(MainStore.Action.SaveContent)
        store.process(MainStore.Action.LoadContent)
    }

    fun next() = io {
        store.process(MainStore.Action.LoadContent)
    }

    override fun onUnknownUserException() = default {
        event.dispatch(MainStore.Event.NavigateToAuthFlow)
    }

}