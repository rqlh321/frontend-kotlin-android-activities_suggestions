package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.SignInUseCase

class SignInUseCaseImpl(
    private val repo: UserRepo
) : SignInUseCase {
    override suspend fun execute(credential: Any) {
        repo.update(UserRepo.UpdateQuery.SignInUserQuery(credential))
    }

}