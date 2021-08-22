package ru.gubatenko.mvi

class SideEffects<Action : Any, SideAction : Any> private constructor() {

    private val sideEffectsMap = hashMapOf<Class<out Action>, Any>()

    suspend fun execute(action: Action, callback: suspend ((SideAction) -> Unit) = {}) {
        val value = sideEffectsMap[action.javaClass] as SideEffect<Action, SideAction>
        value.execute(action, callback)
    }

    class Builder<Action : Any, SideAction : Any> {

        private val sideEffects = SideEffects<Action, SideAction>()

        fun appendSideEffect(
            sideEffect: SideEffect<out Action, out SideAction>
        ): Builder<Action, SideAction> {
            sideEffects.sideEffectsMap[sideEffect.actionId()] = sideEffect
            return this
        }

        fun build(): SideEffects<Action, SideAction> = sideEffects
    }
}
