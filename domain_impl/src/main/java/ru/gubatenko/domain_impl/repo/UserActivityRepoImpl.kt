package ru.gubatenko.domain_impl.repo

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.entity.toDomain
import ru.gubatenko.data.entity.toStored

class UserActivityRepoImpl(
    private val dao: ActivityDao,
) : UserActivityRepo {

    override suspend fun create(value: Activity) = dao.save(value.toStored())

    override suspend fun read() = dao.activities().map { it.toDomain() }

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}