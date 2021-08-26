package ru.gubatenko.domain_impl.repo

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.UserActivityRepo

class UserActivityRepoImpl(
    private val dao: ActivityDao,
    private val toStored: Mapper<Activity, ActivityStored>,
    private val toDomain: Mapper<ActivityStored, Activity>,
) : UserActivityRepo {

    override suspend fun create(value: Activity) = dao.save(toStored.map(value))

    override suspend fun read() = dao.activities().map(toDomain::map)

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}