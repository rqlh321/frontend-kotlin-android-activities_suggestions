package com.example.domain_android.work

import android.content.Context
import androidx.work.*
import com.example.audit.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase
import com.example.domain_android.usecase.LongTermWorkUseCaseImpl.Companion.SYNC_JOB_TAG
import java.util.concurrent.TimeUnit

class UploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    companion object {
        fun Context.runUploadWorker() {
            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val uploadWorkRequest = PeriodicWorkRequestBuilder<UploadWorker>(
                repeatInterval = 1,
                repeatIntervalTimeUnit = TimeUnit.MINUTES,
                flexTimeInterval = 10,
                flexTimeIntervalUnit = TimeUnit.SECONDS
            )
                .addTag(SYNC_JOB_TAG)
                .setConstraints(constraint)
                .build()

            WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork(
                    "MyAppNameBackgroundSync",
                    ExistingPeriodicWorkPolicy.KEEP,
                    uploadWorkRequest
                )
        }
    }

    private val useCase: SyncActivitiesWithServerUseCase by inject()
    private val logger: Logger by inject()

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: UnknownUserException) {
            logger.d(e)
            Result.failure()
        } catch (e: Exception) {
            logger.d(e)
            Result.retry()
        }
    }
}