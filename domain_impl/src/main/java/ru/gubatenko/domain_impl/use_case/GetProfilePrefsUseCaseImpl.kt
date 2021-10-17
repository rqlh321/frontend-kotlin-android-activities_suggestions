package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.Preference
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.model.Pref
import ru.gubatenko.domain.model.Pref.Companion.DARK_THEM_ENABLED_KEY
import ru.gubatenko.domain.model.SwitchPref
import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.GetProfilePrefsUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class GetProfilePrefsUseCaseImpl(
    private val prefs: Preference,
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val userRepo: UserRepo,
) : GetProfilePrefsUseCase {

    override suspend fun execute(): List<Pref> = if (
        userRepo.read(UserRepo.ReadQuery.GetSignedInUserQuery).isNotEmpty()
    ) prefAuthUser() else prefsUnAuthUser()


    private suspend fun prefsUnAuthUser() = listOf(
        SwitchPref(
            id = DARK_THEM_ENABLED_KEY,
            title = getStaticTextUseCase.execute(TextKey.Profile.PREF_NAME_THEME),
            isOn = prefs.getBoolean(DARK_THEM_ENABLED_KEY)
        )
    )

    private suspend fun prefAuthUser() = listOf(
        SwitchPref(
            id = DARK_THEM_ENABLED_KEY,
            title = getStaticTextUseCase.execute(TextKey.Profile.PREF_NAME_THEME),
            isOn = prefs.getBoolean(DARK_THEM_ENABLED_KEY)
        )
    )
}
