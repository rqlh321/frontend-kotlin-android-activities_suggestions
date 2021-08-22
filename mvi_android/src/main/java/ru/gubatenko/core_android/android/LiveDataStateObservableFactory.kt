package ru.gubatenko.core_android.android

import ru.gubatenko.mvi.StateObservable

class LiveDataStateObservableFactory : StateObservable.Factory {

    override fun <State : Any> create(initialState: State): StateObservable<State> {
        return LiveDataStateObservable(initialState)
    }
}
