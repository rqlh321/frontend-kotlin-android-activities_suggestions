package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.data.text.DynamicText
import ru.gubatenko.domain.usecase.GetDynamicTextUseCase

class GetDynamicTextUseCaseImpl(
    private val dynamicText: DynamicText
) : GetDynamicTextUseCase {
    override suspend fun execute(key: String) = dynamicText.value(key)
}