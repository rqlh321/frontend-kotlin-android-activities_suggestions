package ru.gubatenko.mvi_android.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gubatenko.mvi.StateObservable

class LiveDataStateObservable<State : Any>(initialState: State) : StateObservable<State> {

    private val mutableLiveData = MutableLiveData(initialState)
    val liveData: LiveData<State> = mutableLiveData

    override var stateValue: State
        get() = mutableLiveData.value!!
        set(value) {
            mutableLiveData.postValue(value)
        }

    fun observe(lifecycleOwner: LifecycleOwner, observer: (State) -> Unit) {
        mutableLiveData.observe(lifecycleOwner, observer)
    }
}
