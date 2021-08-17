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
        viewModelScope.launch(Dispatchers.IO) {
            store.process(MainStore.Action.LoadContent)
        }
    }

    fun onContentClick() {
        viewModelScope.launch {
            store.process(MainStore.Action.ClickOnContent)
        }
    }
}