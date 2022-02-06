package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.data.pref.AuthPreference
import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase

class AuthOfferIsViewedUseCaseImpl(
    private val prefs: AuthPreference,
) : AuthOfferIsViewedUseCase {

    override suspend fun execute() = prefs.isUserRejectedAuthorizationOffer(true)

}
