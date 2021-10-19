package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.DefinedPreference
import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase

class SaveActivityToLocalStorageUseCaseImpl(
    private val repo: IdeaRepo,
    private val prefs: DefinedPreference,
) : SaveActivityToLocalStorageUseCase {

    override suspend fun execute(activity: Activity) {
        repo.create(IdeaRepo.CreateQuery.NewActivityToLocalStorage(listOf(activity)))
        if (!prefs.isUserRejectedAuthorizationOffer()) {
            throw UnknownUserException()
        }
    }

}
