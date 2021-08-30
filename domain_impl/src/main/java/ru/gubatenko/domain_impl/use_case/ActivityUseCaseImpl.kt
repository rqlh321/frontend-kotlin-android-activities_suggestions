package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase

class ActivityUseCaseImpl(
    private val repo: ActivityRepo,
) : ActivityUseCase {

    override suspend fun activity() = repo
        .read(ActivityRepo.ReadQuery.NewActivityFromSourceServerReadQuery)
        .first()

    override suspend fun save(activity: Activity) = repo
        .create(ActivityRepo.CreateQuery.NewActivityToLocalStorage(listOf(activity)))

    override suspend fun sync() {
        val data = repo.read(ActivityRepo.ReadQuery.ActivityFromLocalStorageReadQuery)
        repo.create(ActivityRepo.CreateQuery.NewActivityToWebStorage(data))
    }
}