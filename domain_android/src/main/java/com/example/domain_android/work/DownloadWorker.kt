package com.example.domain_android.work

import android.content.Context
import androidx.work.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gubatenko.domain.usecase.SyncLocalDatabaseUseCase
import com.example.domain_android.usecase.LongTermWorkUseCaseImpl.Companion.SYNC_JOB_TAG
import com.example.audit.Logger

class DownloadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    companion object {
        fun Context.runDownloadWorker() {
            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val uploadWorkRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
                .setConstraints(constraint)
                .addTag(SYNC_JOB_TAG)
                .build()

            WorkManager.getInstance(this)
                .enqueue(uploadWorkRequest)
        }
    }

    private val useCase: SyncLocalDatabaseUseCase by inject()
    private val logger: Logger by inject()

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: Exception) {
            logger.d(e)
            Result.retry()
        }
    }
}