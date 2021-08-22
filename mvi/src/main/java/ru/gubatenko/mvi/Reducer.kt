package ru.gubatenko.mvi

typealias Reducer<State, SideAction> = (currentState: State, newAction: SideAction) -> State
