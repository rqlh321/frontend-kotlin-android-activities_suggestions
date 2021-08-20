package ru.gubatenko.feature_main_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gubatenko.core.SideEffects
import ru.gubatenko.core_android.android.LiveDataEventDispatcher
import ru.gubatenko.core_android.android.LiveDataStateObservableFactory
import ru.gubatenko.feature_main.ClickOnMainContentSideEffect
import ru.gubatenko.feature_main.LoadMainContentSideEffect
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.feature_main.RefreshMainContentSideEffect
import ru.gubatenko.repo_impl.ActivityRepoImpl
import ru.gubatenko.use_case_impl.GreetingUseCaseImpl

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val event = LiveDataEventDispatcher<MainStore.Event>()
        val useCase = GreetingUseCaseImpl(
            repo = ActivityRepoImpl()
        )
        val sideEffects = SideEffects.Builder<MainStore.Action, MainStore.SideAction>()
            .appendSideEffect(
                sideEffect = LoadMainContentSideEffect(
                    useCase = useCase
                )
            )
            .appendSideEffect(sideEffect = ClickOnMainContentSideEffect(eventDispatcher = event))
            .appendSideEffect(
                sideEffect = RefreshMainContentSideEffect(
                    eventDispatcher = event,
                    useCase = useCase
                )
            )
            .build()

        val store = MainStore(
            sideEffects = sideEffects,
            stateObservableFactory = LiveDataStateObservableFactory(),
        )
        return MainViewModel(
            event = event,
            store = store
        ) as T
    }
}