package ru.gubatenko.mvi

interface StateObservable<State : Any> {

    var stateValue: State

}

class StubStateObservable<T : Any>(override var stateValue: T) : StateObservable<T>
