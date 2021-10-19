package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase

class GetAllSavedActivitiesUseCaseImpl(
    private val repo: IdeaRepo,
) : GetAllPromiseUseCase {
    override suspend fun execute() = repo.subscribe(IdeaRepo.SubscribeQuery.ActivityFromLocalStorage)
}