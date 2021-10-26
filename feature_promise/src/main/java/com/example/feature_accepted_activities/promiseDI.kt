package com.example.feature_accepted_activities

import com.example.feature_accepted_activities.side_effects.LoadPromisesSideEffect
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.SideEffects

const val PROMISE_STATE_OBSERVABLE = "PROMISE_STATE_OBSERVABLE"
const val PROMISE_EVENT_DISPATCHER = "PROMISE_EVENT_DISPATCHER"
const val PROMISE_SIDE_EFFECTS = "PROMISE_SIDE_EFFECTS"
val promiseStoreModuleDI = module {
    single (named(PROMISE_SIDE_EFFECTS)){
        SideEffects.Builder<PromiseStore.Action, PromiseStore.SideAction>()
            .append(sideEffect = LoadPromisesSideEffect(getAllPromiseUseCase = get(), getStaticTextUseCase = get()))
            .build()
    }
    single {
        PromiseStore(
            logger = get(),
            sideEffects = get(named(PROMISE_SIDE_EFFECTS)),
            stateObservable = get(named(PROMISE_STATE_OBSERVABLE)),
        )
    }
}