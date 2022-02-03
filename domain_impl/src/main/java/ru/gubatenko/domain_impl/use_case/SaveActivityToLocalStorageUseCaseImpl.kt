package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.model.Idea
import ru.gubatenko.domain.pref.AuthPreference
import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase

class SaveActivityToLocalStorageUseCaseImpl(
    private val repo: IdeaRepo,
    private val prefs: AuthPreference,
) : SaveActivityToLocalStorageUseCase {

    override suspend fun execute(idea: Idea) {
        repo.create(IdeaRepo.CreateQuery.NewActivityToLocalStorage(listOf(idea)))
        if (!prefs.isUserRejectedAuthorizationOffer()) {
            throw UnknownUserException()
        }
    }

}
