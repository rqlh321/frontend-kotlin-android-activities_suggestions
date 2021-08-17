package ru.gubatenko.core_android.android

import ru.gubatenko.core.StateObservable

class LiveDataStateObservableFactory : StateObservable.Factory {

    override fun <State : Any> create(initialState: State): StateObservable<State> {
        return LiveDataStateObservable(initialState)
    }
}
