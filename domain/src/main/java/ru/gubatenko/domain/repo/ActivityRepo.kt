package ru.gubatenko.domain.repo

import ru.gubatenko.domain.model.Activity

interface ActivityRepo {
    suspend fun create(query: CreateQuery)
    suspend fun read(query: ReadQuery): List<Activity>
    suspend fun update(query: UpdateQuery)
    suspend fun delete(query: DeleteQuery)

    sealed class CreateQuery {
        data class NewActivityToLocalStorage(val activities: List<Activity>) : CreateQuery()
        data class NewActivityToWebStorage(val activities: List<Activity>) : CreateQuery()
    }

    sealed class ReadQuery {
        object NewActivityFromSourceServerReadQuery : ReadQuery()
        object ActivityFromLocalStorageReadQuery : ReadQuery()
        object NotSyncedActivityFromLocalStorageReadQuery : ReadQuery()
    }

    sealed class UpdateQuery {
        data class AllActivitiesSynced(val activities: List<Activity>) : UpdateQuery()
    }

    sealed class DeleteQuery

}