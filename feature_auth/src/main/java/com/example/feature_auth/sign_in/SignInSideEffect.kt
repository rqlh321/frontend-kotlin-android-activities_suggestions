package com.example.feature_auth.sign_in

import ru.gubatenko.domain.usecase.LongTermWorkUseCase
import ru.gubatenko.domain.usecase.SignInUseCase
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class SignInSideEffect(
    private val longTermWorkUseCase: LongTermWorkUseCase,
    private val signInUseCase: SignInUseCase,
    private val eventDispatcher: EventDispatcher<SignInStore.Event>
) : SideEffect<SignInStore.Action.SignIn, SignInStore.SideAction> {

    override fun actionId() = SignInStore.Action.SignIn::class.java

    override suspend fun execute(
        action: SignInStore.Action.SignIn,
        reducerCallback: suspend (SignInStore.SideAction) -> Unit
    ) {
        try {
            signInUseCase.execute(action.credential)
            longTermWorkUseCase.execute(LongTermWorkUseCase.Query.StartUpload)
            longTermWorkUseCase.execute(LongTermWorkUseCase.Query.StartDownload)
            eventDispatcher.dispatch(SignInStore.Event.SignInSuccess)
        } catch (e: Exception) {
            eventDispatcher.dispatch(SignInStore.Event.SignInFail)
        }
    }
}
