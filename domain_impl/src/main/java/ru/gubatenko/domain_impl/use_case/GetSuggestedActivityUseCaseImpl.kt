package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase

class GetSuggestedActivityUseCaseImpl(
    private val repo: IdeaRepo,
) : GetSuggestedActivityUseCase {

    override suspend fun execute() = repo
        .read(IdeaRepo.ReadQuery.NewActivityFromSourceServerReadQuery)
        .first()

}