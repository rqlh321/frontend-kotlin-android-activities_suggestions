package ru.gubatenko.use_case_impl

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.GreetingUseCase

class GreetingUseCaseImpl(
    private val repo: ActivityRepo
) : GreetingUseCase {
    override suspend fun activity(): Activity {
        val activity = repo.read()

        val result = activity.copy(
            activity = activity.activity,
            type = activity.type.uppercase(),
        )

        return result
    }
}