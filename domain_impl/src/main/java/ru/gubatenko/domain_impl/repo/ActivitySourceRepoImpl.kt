package ru.gubatenko.domain_impl.repo

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.service.ActivitySourceService
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo

class ActivitySourceRepoImpl(
    private val service: ActivitySourceService,
    private val toDomain: Mapper<ActivityDto, Activity>,
) : ActivitySourceRepo {

    override suspend fun create(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun read() = toDomain.map(service.activity())

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}