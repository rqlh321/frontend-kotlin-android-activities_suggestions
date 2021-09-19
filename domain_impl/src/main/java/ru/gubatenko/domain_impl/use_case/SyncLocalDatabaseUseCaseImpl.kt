package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.SyncLocalDatabaseUseCase

class SyncLocalDatabaseUseCaseImpl(
    private val repo: ActivityRepo,
) : SyncLocalDatabaseUseCase {
    override suspend fun execute() {
        val data = repo.read(ActivityRepo.ReadQuery.GetUserActionsFromRemoteStorageReadQuery)
        repo.create(ActivityRepo.CreateQuery.NewActivityToLocalStorage(data))
    }
}