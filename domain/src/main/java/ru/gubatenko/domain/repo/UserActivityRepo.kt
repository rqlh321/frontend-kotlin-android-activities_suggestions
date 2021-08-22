package ru.gubatenko.domain.repo

import ru.gubatenko.domain.model.Activity

interface UserActivityRepo {
    suspend fun create(value: Activity)
    suspend fun read(): List<Activity>
    suspend fun update(value: Activity)
    suspend fun delete(value: Activity)
}