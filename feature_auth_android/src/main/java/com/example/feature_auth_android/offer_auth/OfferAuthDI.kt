package com.example.feature_auth_android.offer_auth

import com.example.feature_auth.OfferAuthStore
import com.example.feature_auth.side_effects.AcceptAuthOfferSideEffect
import com.example.feature_auth.side_effects.FetchAuthScreenDataSideEffect
import com.example.feature_auth.side_effects.RejectAuthOfferSideEffect
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val OFFER_AUTH_STATE_OBSERVABLE = "OFFER_AUTH_STATE_OBSERVABLE"
private const val OFFER_AUTH_EVENT_DISPATCHER = "OFFER_AUTH_EVENT_DISPATCHER"
private const val OFFER_AUTH_SIDE_EFFECTS = "OFFER_AUTH_SIDE_EFFECTS"
val offerAuthAndroidModuleDI = module {
    single<StateObservable<OfferAuthStore.State>> (named(OFFER_AUTH_STATE_OBSERVABLE)) { LiveDataStateObservable(OfferAuthStore.State()) }
    single<EventDispatcher<OfferAuthStore.Event>> (named(OFFER_AUTH_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single(named(OFFER_AUTH_SIDE_EFFECTS)) {
        SideEffects.Builder<OfferAuthStore.Action, OfferAuthStore.SideAction>()
            .append(sideEffect = FetchAuthScreenDataSideEffect(useCase = get()))
            .append(sideEffect = RejectAuthOfferSideEffect(useCase = get()))
            .append(sideEffect = AcceptAuthOfferSideEffect(useCase = get(), eventDispatcher = get(named(OFFER_AUTH_EVENT_DISPATCHER))))
            .build()
    }
    single {
        OfferAuthStore(
            sideEffects = get(named(OFFER_AUTH_SIDE_EFFECTS)),
            stateObservable = get(named(OFFER_AUTH_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        OfferAuthViewModel(
            dispatcher = get(named(OFFER_AUTH_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}