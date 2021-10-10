package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.Preference
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.model.Pref
import ru.gubatenko.domain.model.Pref.Companion.PREF_THEME_ID
import ru.gubatenko.domain.model.SwitchPref
import ru.gubatenko.domain.model.User
import ru.gubatenko.domain.usecase.GetProfilePrefsUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class GetProfilePrefsUseCaseImpl(
    private val prefs: Preference,
    private val getStaticTextUseCase: GetStaticTextUseCase,
) : GetProfilePrefsUseCase {

    override suspend fun execute(user: User?): List<Pref> = if (user != null)
        prefAuthUser() else prefsUnAuthUser()


    private fun prefsUnAuthUser(): List<SwitchPref> = emptyList()

    private suspend fun prefAuthUser() = listOf(
        SwitchPref(
            id = PREF_THEME_ID,
            title = getStaticTextUseCase.execute(TextKey.Profile.PREF_NAME_THEME),
            isOn = prefs.isDarkThemeEnabled()
        )
    )
}
