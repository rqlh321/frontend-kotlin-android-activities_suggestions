package ru.gubatenko.repo_impl

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.UserActivityRepo

class UserActivityRepoImpl : UserActivityRepo {

    override suspend fun create(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun read(): List<Activity> {
        TODO("Not yet implemented")
    }

    override suspend fun update(value: Activity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Activity) {
        TODO("Not yet implemented")
    }
}