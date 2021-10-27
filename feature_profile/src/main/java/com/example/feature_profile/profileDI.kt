package com.example.feature_profile

import com.example.feature_profile.side_effects.ClickOnSignInSideEffect
import com.example.feature_profile.side_effects.ClickOnSignOutSideEffect
import com.example.feature_profile.side_effects.LoadProfileSideEffect
import com.example.feature_profile.side_effects.SwitchPrefSideEffect
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.SideEffects

const val PROFILE_STATE_OBSERVABLE = "PROFILE_STATE_OBSERVABLE"
const val PROFILE_EVENT_DISPATCHER = "PROFILE_EVENT_DISPATCHER"
const val PROFILE_SIDE_EFFECTS = "PROFILE_SIDE_EFFECTS"
val profileFeatureModuleDI = module {
    single (named(PROFILE_SIDE_EFFECTS)){
        SideEffects.Builder<ProfileStore.Action, ProfileStore.SideAction>()
            .append(sideEffect = ClickOnSignInSideEffect(eventDispatcher = get(named(PROFILE_EVENT_DISPATCHER))))
            .append(sideEffect = SwitchPrefSideEffect(eventDispatcher = get(named(PROFILE_EVENT_DISPATCHER)), setPrefUseCase = get(), getProfilePrefsUseCase = get()))
            .append(sideEffect = ClickOnSignOutSideEffect(signOutUseCase = get(), longTermWorkUseCase = get(), getStaticTextUseCase = get()))
            .append(sideEffect = LoadProfileSideEffect(getSignedInUserUseCase = get(), getStaticTextUseCase = get(), getProfilePrefsUseCase = get()))
            .build()
    }
    single {
        ProfileStore(
            logger = get(),
            sideEffects = get(named(PROFILE_SIDE_EFFECTS)),
            stateObservable = get(named(PROFILE_STATE_OBSERVABLE)),
        )
    }

}
