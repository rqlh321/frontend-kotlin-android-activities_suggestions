package ru.gubatenko.domain_impl.data.dao

import ru.gubatenko.domain_impl.data.entity.ActivityStored

interface ActivityDao {
    suspend fun save(activity: ActivityStored)
    suspend fun activities(): List<ActivityStored>
}