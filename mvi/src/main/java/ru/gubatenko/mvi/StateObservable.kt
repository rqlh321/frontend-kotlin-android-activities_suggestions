package ru.gubatenko.mvi

interface StateObservable<State : Any> {

    var stateValue: State

    interface Factory {
        fun <State : Any> create(initialState: State): StateObservable<State>
    }
}
