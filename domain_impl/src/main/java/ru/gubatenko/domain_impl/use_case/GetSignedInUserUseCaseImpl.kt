package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.GetSignedInUserUseCase

class GetSignedInUserUseCaseImpl(
    private val repo: UserRepo,
) : GetSignedInUserUseCase {

    override suspend fun execute() = repo.read(UserRepo.ReadQuery.GetSignedInUserQuery)
        .firstOrNull()

}