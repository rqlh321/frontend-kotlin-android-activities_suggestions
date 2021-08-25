package ru.gubatenko.data.dao

import ru.gubatenko.data.entity.ActivityStored

interface ActivityDao {
    suspend fun save(activity: ActivityStored)
    suspend fun activities(): List<ActivityStored>
}