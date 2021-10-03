package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase

class GetSuggestedActivityUseCaseImpl(
    private val repo: ActivityRepo,
) : GetSuggestedActivityUseCase {

    override suspend fun execute() = repo
        .read(ActivityRepo.ReadQuery.NewActivityFromSourceServerReadQuery)
        .first()

}