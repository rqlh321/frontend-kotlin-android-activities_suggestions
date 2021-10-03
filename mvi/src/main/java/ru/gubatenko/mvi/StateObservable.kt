package ru.gubatenko.mvi

interface StateObservable<State : Any> {

    var stateValue: State

}
