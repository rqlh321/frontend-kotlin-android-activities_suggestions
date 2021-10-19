package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.SignOutUseCase

class SignOutUseCaseImpl(
    private val userRepo: UserRepo,
    private val ideaRepo: IdeaRepo,
) : SignOutUseCase {

    override suspend fun execute() {
        userRepo.update(UserRepo.UpdateQuery.SignOutUserQuery)
        ideaRepo.delete(IdeaRepo.DeleteQuery.ClearLocalStorage)
    }

}