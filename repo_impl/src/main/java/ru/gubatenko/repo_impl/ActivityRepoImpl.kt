package ru.gubatenko.repo_impl

import ru.gubatenko.data.ActivityService
import ru.gubatenko.data.ServiceFactory
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivityRepo

class ActivityRepoImpl : ActivityRepo {

    private val service: ActivityService = ServiceFactory.activity()
    private val mapper: Mapper<ActivityDto, Activity> = ActivityDataToDomain()

    override suspend fun create(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun read() = mapper.convert(service.activity())

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}