package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.usecase.SyncLocalDatabaseUseCase

class SyncLocalDatabaseUseCaseImpl(
    private val repo: IdeaRepo,
) : SyncLocalDatabaseUseCase {
    override suspend fun execute() {
        val data = repo.read(IdeaRepo.ReadQuery.GetUserActionsFromRemoteStorageReadQuery)
        repo.create(IdeaRepo.CreateQuery.NewActivityToLocalStorage(data))
    }
}