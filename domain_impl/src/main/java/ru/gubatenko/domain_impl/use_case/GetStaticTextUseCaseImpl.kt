package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.data.text.StaticText
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class GetStaticTextUseCaseImpl(
    private val staticText: StaticText
) : GetStaticTextUseCase {
    override suspend fun execute(key: String) = staticText.value(key)
}