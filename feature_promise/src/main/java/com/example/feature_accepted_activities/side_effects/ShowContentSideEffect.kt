package com.example.feature_accepted_activities.side_effects

import com.example.feature_accepted_activities.PromiseStore
import kotlinx.coroutines.flow.collect
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.mvi.SideEffect

class ShowContentSideEffect(
    private val useCase: GetAllPromiseUseCase,
    private val getStaticTextUseCase: GetStaticTextUseCase,
) : SideEffect<PromiseStore.Action.LoadContent, PromiseStore.SideAction> {

    override fun actionId() = PromiseStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: PromiseStore.Action.LoadContent,
        reducerCallback: suspend (PromiseStore.SideAction) -> Unit
    ) {
        val noPromiseInfoText = getStaticTextUseCase.execute(TextKey.Promise.EMPTY_LIST)
        useCase.execute().collect { promiseList ->
            reducerCallback.invoke(
                PromiseStore.SideAction.LoadSuccess(
                    promiseList = promiseList,
                    isPromiseListVisible = promiseList.isNotEmpty(),
                    infoText = noPromiseInfoText,
                    isInfoTextVisible = promiseList.isEmpty(),
                )
            )
        }
    }
}
