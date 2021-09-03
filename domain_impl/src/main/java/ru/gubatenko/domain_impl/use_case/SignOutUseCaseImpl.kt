package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.SignOutUseCase

class SignOutUseCaseImpl(
    private val repo: UserRepo,
) : SignOutUseCase {

    override suspend fun execute() = repo.update(UserRepo.UpdateQuery.SignOutUserQuery)

}