package ru.gubatenko.core_android.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.gubatenko.core.EventDispatcher

class LiveDataEventDispatcher<T : Any> : EventDispatcher<T> {

    private val singleLiveEvent = SingleLiveEvent<T>()

    override suspend fun dispatch(event: T) {
        withContext(Dispatchers.Main) {
            singleLiveEvent.value = (event)
        }
    }

    fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        singleLiveEvent.observe(lifecycleOwner, Observer(observer::invoke))
    }
}
