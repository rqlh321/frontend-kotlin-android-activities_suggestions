package com.example.feature_auth_android.sign_in

import com.example.feature_auth.sign_in.SignInStore
import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher

class SignInViewModel(
    dispatcher: EventDispatcher<SignInStore.Event>,
    private val store: SignInStore
) : BaseViewModel() {

    val event = (dispatcher as LiveDataEventDispatcher<SignInStore.Event>)

    fun signIn(credential: Any) {
        io {
            store.process(SignInStore.Action.SignIn(credential))
        }
    }
}