package com.example.feature_profile_android

import com.example.feature_profile.ProfileStore
import com.example.feature_profile.side_effects.ClickOnSignInSideEffect
import com.example.feature_profile.side_effects.ClickOnSignOutSideEffect
import com.example.feature_profile.side_effects.GetCurrentUserSideEffect
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val PROFILE_STATE_OBSERVABLE = "PROFILE_STATE_OBSERVABLE"
private const val PROFILE_EVENT_DISPATCHER = "PROFILE_EVENT_DISPATCHER"
private const val PROFILE_SIDE_EFFECTS = "PROFILE_SIDE_EFFECTS"
val profileFeatureAndroidModuleDI = module {
    single<StateObservable<ProfileStore.State>> (named(PROFILE_STATE_OBSERVABLE)) { LiveDataStateObservable(ProfileStore.State()) }
    single<EventDispatcher<ProfileStore.Event>> (named(PROFILE_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single (named(PROFILE_SIDE_EFFECTS)){
        SideEffects.Builder<ProfileStore.Action, ProfileStore.SideAction>()
            .append(sideEffect = ClickOnSignInSideEffect(eventDispatcher = get(named(PROFILE_EVENT_DISPATCHER))))
            .append(sideEffect = ClickOnSignOutSideEffect(useCase = get(), longTermWorkUseCase = get()))
            .append(sideEffect = GetCurrentUserSideEffect(getSignedInUserUseCase = get(), getStaticTextUseCase = get(), getDynamicTextUseCase = get()))
            .build()
    }
    single {
        ProfileStore(
            logger = get(),
            sideEffects = get(named(PROFILE_SIDE_EFFECTS)),
            stateObservable = get(named(PROFILE_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        ProfileViewModel(
            dispatcher = get(named(PROFILE_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}