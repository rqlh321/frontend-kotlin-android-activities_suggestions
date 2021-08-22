package ru.gubatenko.mvi

interface SideEffect<Action : Any, SideAction : Any> {

    fun actionId(): Class<Action> = throw IllegalStateException("actionId must be specified!")

    suspend fun execute(action: Action, reducerCallback: suspend ((SideAction) -> Unit) = {})
}
