package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.Preference
import ru.gubatenko.domain.usecase.SetPrefUseCase

class SetPrefUseCaseImpl(
    private val prefs: Preference,
) : SetPrefUseCase {

    override suspend fun execute(query: SetPrefUseCase.Query) {
        when (query) {
            is SetPrefUseCase.Query.SwitchPrefQuery -> prefs.setBoolean(
                query.id,
                query.value
            )
        }
    }
}
