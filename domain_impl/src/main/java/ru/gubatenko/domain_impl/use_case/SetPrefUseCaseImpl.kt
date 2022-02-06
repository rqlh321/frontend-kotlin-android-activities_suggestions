package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.data.pref.ThemPreference
import ru.gubatenko.domain.usecase.SetPrefUseCase

class SetPrefUseCaseImpl(
    private val prefs: ThemPreference,
) : SetPrefUseCase {

    override suspend fun execute(query: SetPrefUseCase.Query) {
        when (query) {
            is SetPrefUseCase.Query.SwitchPrefQuery -> when (query.id) {
                ThemPreference.DARK_THEM_ENABLED_KEY -> prefs.isDarkThemEnabled(query.value)
            }
        }
    }
}
