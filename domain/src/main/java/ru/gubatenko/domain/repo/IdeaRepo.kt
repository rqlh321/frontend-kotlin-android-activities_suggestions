package ru.gubatenko.domain.repo

import kotlinx.coroutines.flow.Flow
import ru.gubatenko.domain.model.Idea

interface IdeaRepo {
    suspend fun create(query: CreateQuery)
    suspend fun read(query: ReadQuery): List<Idea>
    suspend fun subscribe(query: SubscribeQuery): Flow<List<Idea>>
    suspend fun update(query: UpdateQuery)
    suspend fun delete(query: DeleteQuery)

    sealed class CreateQuery {
        data class NewActivityToLocalStorage(val ideas: List<Idea>) : CreateQuery()
        data class NewActivityToWebStorage(val ideas: List<Idea>) : CreateQuery()
    }

    sealed class ReadQuery {
        object NewActivityFromSourceServerReadQuery : ReadQuery()
        object ActivityFromLocalStorageReadQuery : ReadQuery()
        object GetUserActionsFromRemoteStorageReadQuery : ReadQuery()
        object NotSyncedActivityFromLocalStorageReadQuery : ReadQuery()
    }

    sealed class SubscribeQuery{
        object ActivityFromLocalStorage : SubscribeQuery()
    }

    sealed class UpdateQuery {
        data class AllActivitiesSynced(val ideas: List<Idea>) : UpdateQuery()
    }

    sealed class DeleteQuery {
        object ClearLocalStorage : IdeaRepo.DeleteQuery()
    }

}