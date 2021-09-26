package com.example.feature_auth.offer_auth.side_effects

import com.example.feature_auth.offer_auth.OfferAuthStore
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.mvi.SideEffect

class FetchAuthScreenDataSideEffect(
    private val useCase: GetStaticTextUseCase,
) : SideEffect<OfferAuthStore.Action.FetchAuthScreenData, OfferAuthStore.SideAction> {

    override fun actionId() = OfferAuthStore.Action.FetchAuthScreenData::class.java

    override suspend fun execute(
        action: OfferAuthStore.Action.FetchAuthScreenData,
        reducerCallback: suspend (OfferAuthStore.SideAction) -> Unit
    ) {
        reducerCallback.invoke(
            OfferAuthStore.SideAction.SetupAuthOfferScreen(
                labelText = useCase.execute(TextKey.Auth.OFFER_AUTH_LABEL),
                buttonText = useCase.execute(TextKey.Auth.OFFER_AUTH_BUTTON)
            )
        )
    }
}
