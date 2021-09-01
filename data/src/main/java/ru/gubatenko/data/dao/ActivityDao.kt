package ru.gubatenko.data.dao

import ru.gubatenko.data.entity.ActivityStored

interface ActivityDao<T : ActivityStored> {
    suspend fun saveAll(activity: List<@JvmSuppressWildcards T>)
    suspend fun save(activity: T)
    suspend fun getNotSynced(): List<@JvmSuppressWildcards T>
    suspend fun updateAsSynced(ids: List<Long>)
    suspend fun all(): List<@JvmSuppressWildcards T>
}