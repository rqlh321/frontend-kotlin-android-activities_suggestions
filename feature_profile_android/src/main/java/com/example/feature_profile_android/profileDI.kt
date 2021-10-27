package com.example.feature_profile_android

import com.example.feature_profile.PROFILE_EVENT_DISPATCHER
import com.example.feature_profile.PROFILE_STATE_OBSERVABLE
import com.example.feature_profile.ProfileStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

val profileFeatureAndroidModuleDI = module {
    single<StateObservable<ProfileStore.State>> (named(PROFILE_STATE_OBSERVABLE)) { LiveDataStateObservable(ProfileStore.State()) }
    single<EventDispatcher<ProfileStore.Event>> (named(PROFILE_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    viewModel {
        ProfileViewModel(
            dispatcher = get(named(PROFILE_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}
