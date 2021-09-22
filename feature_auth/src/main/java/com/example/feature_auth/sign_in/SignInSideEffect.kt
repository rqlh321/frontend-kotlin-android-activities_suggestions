package com.example.feature_auth.sign_in

import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class SignInSideEffect(
    private val useCase: AuthOfferIsViewedUseCase,
    private val eventDispatcher: EventDispatcher<SignInStore.Event>
) : SideEffect<SignInStore.Action.SignIn, SignInStore.SideAction> {

    override fun actionId() = SignInStore.Action.SignIn::class.java

    override suspend fun execute(
        action: SignInStore.Action.SignIn,
        reducerCallback: suspend (SignInStore.SideAction) -> Unit
    ) {
        useCase.execute()
        eventDispatcher.dispatch(SignInStore.Event.SignInSuccess)
        eventDispatcher.dispatch(SignInStore.Event.SignInFail)
    }
}
