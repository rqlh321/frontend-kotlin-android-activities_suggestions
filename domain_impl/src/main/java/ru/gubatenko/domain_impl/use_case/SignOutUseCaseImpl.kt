package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.SignOutUseCase

class SignOutUseCaseImpl(
    private val userRepo: UserRepo,
    private val activityRepo: ActivityRepo,
) : SignOutUseCase {

    override suspend fun execute() {
        userRepo.update(UserRepo.UpdateQuery.SignOutUserQuery)
        activityRepo.delete(ActivityRepo.DeleteQuery.ClearLocalStorage)
    }

}