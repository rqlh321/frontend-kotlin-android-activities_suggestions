package ru.gubatenko.core_android.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import ru.gubatenko.core.StateObservable

class LiveDataStateObservable<State : Any>(initialState: State) : StateObservable<State> {

    private val liveData = MutableLiveData(initialState)

    override var stateValue: State
        get() = liveData.value!!
        set(value) {
            liveData.postValue(value)
        }

    fun observe(lifecycleOwner: LifecycleOwner, observer: (State) -> Unit) {
        liveData.observe(lifecycleOwner, observer)
    }
}
