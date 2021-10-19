package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase

class SyncActivitiesWithServerUseCaseImpl(
    private val repo: IdeaRepo,
) : SyncActivitiesWithServerUseCase {
    override suspend fun execute() {
        val data = repo.read(IdeaRepo.ReadQuery.NotSyncedActivityFromLocalStorageReadQuery)
        if (data.isEmpty()) {
            return
        } else {
            repo.create(IdeaRepo.CreateQuery.NewActivityToWebStorage(data))
            repo.update(IdeaRepo.UpdateQuery.AllActivitiesSynced(data))
        }
    }
}