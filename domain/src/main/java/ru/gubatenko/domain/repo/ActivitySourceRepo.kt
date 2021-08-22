package ru.gubatenko.domain.repo

import ru.gubatenko.domain.model.Activity

interface ActivitySourceRepo {
    suspend fun create(value: Activity)
    suspend fun read(): Activity
    suspend fun update(value: Activity)
    suspend fun delete(value: Activity)
}