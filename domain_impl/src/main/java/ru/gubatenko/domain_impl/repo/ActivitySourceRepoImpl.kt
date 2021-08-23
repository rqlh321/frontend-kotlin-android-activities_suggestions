package ru.gubatenko.domain_impl.repo

import ru.gubatenko.domain_impl.data.dto.ActivityDto
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain_impl.data.service.ActivitySourceService

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