package ru.gubatenko.feature_main_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.gubatenko.core_android.android.LiveDataEventDispatcher
import ru.gubatenko.core_android.android.LiveDataStateObservable
import ru.gubatenko.feature_main.MainStore

class MainViewModel(
    val event: LiveDataEventDispatcher<MainStore.Event>,
    private val store: MainStore
) : ViewModel() {

    val state = (store.state as LiveDataStateObservable)

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            store.process(MainStore.Action.LoadContent)
        }
    }

    fun reload() = load()

    fun onContentClick() {
        viewModelScope.launch {
            store.process(MainStore.Action.ClickOnContent)
        }
    }

    fun refresh() {
        throw Exception("unauthorized")
        // viewModelScope.launch(Dispatchers.IO) {
        //     store.process(MainStore.Action.RefreshContent)
        // }
    }
}