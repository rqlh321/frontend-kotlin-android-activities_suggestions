package com.example.feature_auth.offer_auth.side_effects

import com.example.feature_auth.offer_auth.OfferAuthStore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase
import ru.gubatenko.mvi.SideEffect

class RejectAuthOfferSideEffect(
    private val useCase: AuthOfferIsViewedUseCase,
) : SideEffect<OfferAuthStore.Action.RejectAuthOffer, OfferAuthStore.SideAction> {

    override fun actionId() = OfferAuthStore.Action.RejectAuthOffer::class.java

    override suspend fun execute(
        action: OfferAuthStore.Action.RejectAuthOffer,
        reducerCallback: suspend (OfferAuthStore.SideAction) -> Unit
    ) = withContext(IO) { useCase.execute() }
}
