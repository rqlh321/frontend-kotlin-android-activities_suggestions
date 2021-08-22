package ru.gubatenko.feature_main_android

import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.core_android.android.BaseViewModel
import ru.gubatenko.core_android.android.LiveDataEventDispatcher
import ru.gubatenko.core_android.android.LiveDataStateObservable
import ru.gubatenko.feature_main.MainStore

class MainViewModel(
    dispatcher: EventDispatcher<MainStore.Event>,
    private val store: MainStore
) : BaseViewModel() {

    val state = (store.state as LiveDataStateObservable)
    val event = (dispatcher as LiveDataEventDispatcher<MainStore.Event>)

    init {
        load()
    }

    private fun load() = io {
        store.process(MainStore.Action.LoadContent)
    }

    fun reload() = load()

    fun save() = io {
        store.process(MainStore.Action.SaveContent(state.stateValue.action))
        store.process(MainStore.Action.LoadContent)
    }

    fun onContentClick() = default {
        store.process(MainStore.Action.ClickOnContent)
    }

    fun refresh() = io {
        store.process(MainStore.Action.RefreshContent)
    }

    override fun onUnknownUserException() = default {
        event.dispatch(MainStore.Event.NavigateTo(R.id.auth_fragment_id))
    }

    fun onSuccessAuthorization() = default {
        store.process(MainStore.Action.ClickOnContent)
    }
}