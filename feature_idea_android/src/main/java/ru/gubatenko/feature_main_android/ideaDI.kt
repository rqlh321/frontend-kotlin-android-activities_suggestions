package ru.gubatenko.feature_main_android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.feature_main.IDEA_EVENT_DISPATCHER
import ru.gubatenko.feature_main.IDEA_STATE_OBSERVABLE
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

val ideaFeatureAndroidModuleDI = module {
    single<StateObservable<IdeaStore.State>> (named(IDEA_STATE_OBSERVABLE)) { LiveDataStateObservable(IdeaStore.State()) }
    single<EventDispatcher<IdeaStore.Event>> (named(IDEA_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    viewModel {
        IdeaViewModel(
            dispatcher = get(named(IDEA_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}
