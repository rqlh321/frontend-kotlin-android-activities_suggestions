package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.IsAuthorizedUseCase

class IsAuthorizedUseCaseImpl(
    private val repo: UserRepo,
) : IsAuthorizedUseCase {

    override suspend fun execute() = repo.read(UserRepo.ReadQuery.GetSignedInUserQuery).isNotEmpty()

}