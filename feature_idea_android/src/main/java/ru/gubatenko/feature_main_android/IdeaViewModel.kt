package ru.gubatenko.feature_main_android

import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class IdeaViewModel(
    dispatcher: EventDispatcher<IdeaStore.Event>,
    private val store: IdeaStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)
    val event = (dispatcher as LiveDataEventDispatcher<IdeaStore.Event>)

    init {
        io {
            store.process(IdeaStore.Action.LoadContent)
        }
    }

    fun reload() = io {
        store.process(IdeaStore.Action.LoadContent)
    }

    fun save() = io {
        store.process(IdeaStore.Action.SaveContent)
        store.process(IdeaStore.Action.LoadContent)
    }

    fun next() = io {
        store.process(IdeaStore.Action.LoadContent)
    }

    override fun onUnknownUserException() = default {
        event.dispatch(IdeaStore.Event.NavigateToAuthFlow)
    }

}
