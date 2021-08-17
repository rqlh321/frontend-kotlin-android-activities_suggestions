package ru.gubatenko.core

typealias Reducer<State, SideAction> = (currentState: State, newAction: SideAction) -> State
