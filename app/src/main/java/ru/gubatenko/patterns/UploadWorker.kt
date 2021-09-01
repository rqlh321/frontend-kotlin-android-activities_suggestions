package ru.gubatenko.patterns

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase

class UploadWorker(
    appContext: Context, workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val useCase: SyncActivitiesWithServerUseCase by inject()

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}