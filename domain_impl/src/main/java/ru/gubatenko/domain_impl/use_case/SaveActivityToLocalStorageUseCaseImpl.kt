package ru.gubatenko.domain_impl.use_case

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase

class SaveActivityToLocalStorageUseCaseImpl(
    private val repo: ActivityRepo,
) : SaveActivityToLocalStorageUseCase {

    override suspend fun execute(activity: Activity) = repo
        .create(ActivityRepo.CreateQuery.NewActivityToLocalStorage(listOf(activity)))

}