package ru.gubatenko.patterns

import android.content.Context
import androidx.work.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase
import timber.log.Timber
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

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: UnknownUserException) {
            Timber.d(e)
            Result.failure()
        } catch (e: Exception) {
            Timber.d(e)
            Result.retry()
        }
    }
}