package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.data.prefs.Preference
import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase

class AuthOfferIsViewedUseCaseImpl(
    private val prefs: Preference,
) : AuthOfferIsViewedUseCase {

    override suspend fun execute() = prefs.isUserRejectedAuthorizationOffer(true)

}