package ru.gubatenko.domain_impl.repo

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.entity.ActivityStored

class UserActivityRepoImpl(
    private val dao: ActivityDao,
    private val fromDomain: Mapper<Activity, ActivityStored>,
    private val toDomain: Mapper<ActivityStored, Activity>,
) : UserActivityRepo {

    override suspend fun create(value: Activity) = dao.save(fromDomain.convert(value))

    override suspend fun read() = dao.activities().map(toDomain::convert)

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}