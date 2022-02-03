package com.example.feature_auth.offer_auth.side_effects

import com.example.feature_auth.offer_auth.OfferAuthStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class AcceptAuthOfferSideEffect(
    private val useCase: AuthOfferIsViewedUseCase,
    private val eventDispatcher: EventDispatcher<OfferAuthStore.Event>
) : SideEffect<OfferAuthStore.Action.AcceptAuthOffer, OfferAuthStore.SideAction> {

    override fun actionId() = OfferAuthStore.Action.AcceptAuthOffer::class.java

    override suspend fun execute(
        action: OfferAuthStore.Action.AcceptAuthOffer,
        reducerCallback: suspend (OfferAuthStore.SideAction) -> Unit
    ) {
        withContext(Dispatchers.IO) { useCase.execute() }
        eventDispatcher.dispatch(OfferAuthStore.Event.NavigateToAuthorization)
    }
}
