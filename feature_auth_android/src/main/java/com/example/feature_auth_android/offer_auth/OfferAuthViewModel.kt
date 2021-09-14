package com.example.feature_auth_android.offer_auth

import com.example.feature_auth.OfferAuthStore
import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class OfferAuthViewModel(
    dispatcher: EventDispatcher<OfferAuthStore.Event>,
    private val store: OfferAuthStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)
    val event = (dispatcher as LiveDataEventDispatcher<OfferAuthStore.Event>)

    init {
        main {
            store.process(OfferAuthStore.Action.FetchAuthScreenData)
        }
    }

    fun accept() {
        main {
            store.process(OfferAuthStore.Action.AcceptAuthOffer)
        }
    }

    fun reject() {
        main {
            store.process(OfferAuthStore.Action.RejectAuthOffer)
        }
    }
}