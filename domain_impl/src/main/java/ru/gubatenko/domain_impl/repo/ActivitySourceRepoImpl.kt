package ru.gubatenko.domain_impl.repo

import ru.gubatenko.data.dto.toDomain
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.data.service.ActivitySourceService

class ActivitySourceRepoImpl(
    private val service: ActivitySourceService,
) : ActivitySourceRepo {

    override suspend fun create(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun read() = service.activity().toDomain()

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}