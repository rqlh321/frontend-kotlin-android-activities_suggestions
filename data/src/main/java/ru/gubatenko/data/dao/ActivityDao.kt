package ru.gubatenko.data.dao

import ru.gubatenko.data.entity.ActivityStored

interface ActivityDao<T : ActivityStored> {
    suspend fun saveAll(activity: List<@JvmSuppressWildcards T>)
    suspend fun save(activity: T)
    suspend fun activities(): List<@JvmSuppressWildcards T>
}