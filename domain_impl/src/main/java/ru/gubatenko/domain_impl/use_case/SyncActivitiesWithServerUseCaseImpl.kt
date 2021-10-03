package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase

class SyncActivitiesWithServerUseCaseImpl(
    private val repo: ActivityRepo,
) : SyncActivitiesWithServerUseCase {
    override suspend fun execute() {
        val data = repo.read(ActivityRepo.ReadQuery.NotSyncedActivityFromLocalStorageReadQuery)
        if (data.isEmpty()) {
            return
        } else {
            repo.create(ActivityRepo.CreateQuery.NewActivityToWebStorage(data))
            repo.update(ActivityRepo.UpdateQuery.AllActivitiesSynced(data))
        }
    }
}