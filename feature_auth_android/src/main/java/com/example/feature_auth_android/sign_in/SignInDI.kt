package com.example.feature_auth_android.sign_in

import com.example.feature_auth.sign_in.SignInSideEffect
import com.example.feature_auth.sign_in.SignInStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val SIGN_IN_STATE_OBSERVABLE = "SIGN_IN_STATE_OBSERVABLE"
private const val SIGN_IN_EVENT_DISPATCHER = "SIGN_IN_EVENT_DISPATCHER"
private const val SIGN_IN_SIDE_EFFECTS = "SIGN_IN_SIDE_EFFECTS"
val signInFeatureAndroidModuleDI = module {
    single<StateObservable<SignInStore.State>> (named(SIGN_IN_STATE_OBSERVABLE)){ LiveDataStateObservable(SignInStore.State()) }
    single<EventDispatcher<SignInStore.Event>> (named(SIGN_IN_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single (named(SIGN_IN_SIDE_EFFECTS)){
        SideEffects.Builder<SignInStore.Action, SignInStore.SideAction>()
            .append(sideEffect = SignInSideEffect(eventDispatcher = get(named(
                SIGN_IN_EVENT_DISPATCHER
            )), longTermWorkUseCase = get(), signInUseCase = get()))
            .build()
    }
    single {
        SignInStore(
            logger = get(),
            sideEffects = get(named(SIGN_IN_SIDE_EFFECTS)),
            stateObservable = get(named(SIGN_IN_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        SignInViewModel(
            dispatcher = get(named(SIGN_IN_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}