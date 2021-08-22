package ru.gubatenko.repo_impl

import ru.gubatenko.data.ActivitySourceService
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.Mapper

class ActivitySourceRepoImpl(
    private val service: ActivitySourceService,
    private val mapper: Mapper<ActivityDto, Activity>
) : ActivitySourceRepo {

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