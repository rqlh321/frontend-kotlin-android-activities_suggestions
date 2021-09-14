package ru.gubatenko.common_android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.gubatenko.domain.exception.UnknownUserException

abstract class BaseViewModel : ViewModel() {

    protected fun io(block: suspend () -> Unit) = suspendFun(Dispatchers.IO, block)
    protected fun default(block: suspend () -> Unit) = suspendFun(Dispatchers.Default, block)
    protected fun main(block: suspend () -> Unit) = suspendFun(Dispatchers.Main, block)
    protected fun unconfined(block: suspend () -> Unit) = suspendFun(Dispatchers.Unconfined, block)

    protected open fun onUnknownUserException() = Unit

    private fun suspendFun(
        dispatcher: CoroutineDispatcher,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is UnknownUserException -> onUnknownUserException()
                }
                Log.d(this::class.java.simpleName, e.message ?: e::class.java.simpleName)
            }
        }
    }
}