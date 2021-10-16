package com.example.feature_frame_android

import com.example.feature_mainframe.MainframeStore
import com.example.feature_mainframe.SetupMainframeSideEffect
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val MAINFRAME_STATE_OBSERVABLE = "MAINFRAME_STATE_OBSERVABLE"
private const val MAINFRAME_EVENT_DISPATCHER = "MAINFRAME_EVENT_DISPATCHER"
private const val MAINFRAME_SIDE_EFFECTS = "MAINFRAME_SIDE_EFFECTS"

val mainframeFeatureAndroidModuleDI = module {
    single<StateObservable<MainframeStore.State>>(named(MAINFRAME_STATE_OBSERVABLE)) { LiveDataStateObservable(MainframeStore.State()) }
    single<EventDispatcher<MainframeStore.Event>>(named(MAINFRAME_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }
    single(named(MAINFRAME_SIDE_EFFECTS)) {
        SideEffects.Builder<MainframeStore.Action, MainframeStore.SideAction>()
            .append(sideEffect = SetupMainframeSideEffect(preference = get()))
            .build()
    }
    single {
        MainframeStore(
            logger = get(),
            sideEffects = get(named(MAINFRAME_SIDE_EFFECTS)),
            stateObservable = get(named(MAINFRAME_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        MainframeViewModel(
            dispatcher = get(named(MAINFRAME_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}
