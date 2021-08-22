package ru.gubatenko.domain_impl

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase

class ActivityUseCaseImpl(
    private val activityRepo: ActivitySourceRepo,
    private val userActivityRepo: UserActivityRepo,
) : ActivityUseCase {
    override suspend fun activity() = activityRepo.read()

    override suspend fun save(activity: Activity) = userActivityRepo.create(activity)
}