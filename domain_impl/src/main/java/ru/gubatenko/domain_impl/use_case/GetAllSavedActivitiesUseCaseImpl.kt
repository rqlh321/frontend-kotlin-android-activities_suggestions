package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.GetAllSavedActivitiesUseCase

class GetAllSavedActivitiesUseCaseImpl(
    private val repo: ActivityRepo,
) : GetAllSavedActivitiesUseCase {
    override suspend fun execute() = repo.subscribe(ActivityRepo.SubscribeQuery.ActivityFromLocalStorage)
}