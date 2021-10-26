package ru.gubatenko.feature_main

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.feature_main.side_effects.ClickOnSaveSideEffect
import ru.gubatenko.feature_main.side_effects.LoadIdeaSideEffect
import ru.gubatenko.mvi.SideEffects

const val IDEA_STATE_OBSERVABLE = "IDEA_STATE_OBSERVABLE"
const val IDEA_EVENT_DISPATCHER = "IDEA_EVENT_DISPATCHER"
const val IDEA_SIDE_EFFECTS = "IDEA_SIDE_EFFECTS"
val ideaStoreModuleDI = module {
    single (named(IDEA_SIDE_EFFECTS)){
        SideEffects.Builder<IdeaStore.Action, IdeaStore.SideAction>()
            .append(sideEffect = LoadIdeaSideEffect(getStaticTextUseCase = get(), getSuggestedActivityUseCase = get()))
            .append(sideEffect = ClickOnSaveSideEffect(saveActivityToLocalStorageUseCase = get(), stateObservable = get(named(IDEA_STATE_OBSERVABLE))))
            .build()
    }
    single {
        IdeaStore(
            logger = get(),
            sideEffects = get(named(IDEA_SIDE_EFFECTS)),
            stateObservable = get(named(IDEA_STATE_OBSERVABLE)),
        )
    }
}