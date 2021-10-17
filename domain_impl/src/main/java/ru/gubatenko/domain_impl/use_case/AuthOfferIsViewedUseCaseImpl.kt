package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.DefinedPreference
import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase

class AuthOfferIsViewedUseCaseImpl(
    private val prefs: DefinedPreference,
) : AuthOfferIsViewedUseCase {

    override suspend fun execute() = prefs.isUserRejectedAuthorizationOffer(true)

}
