package ru.gubatenko.data.dao

import kotlinx.coroutines.flow.Flow
import ru.gubatenko.data.entity.IdeaStored

interface IdeaDao<T : IdeaStored> {
    suspend fun delete()
    suspend fun save(idea: List<@JvmSuppressWildcards T>)
    suspend fun save(idea: T)
    suspend fun getNotSynced(): List<@JvmSuppressWildcards T>
    suspend fun updateAsSynced(ids: List<Long>)
    suspend fun all(): List<@JvmSuppressWildcards T>
    fun subscribe(): Flow<List<@JvmSuppressWildcards T>>
}