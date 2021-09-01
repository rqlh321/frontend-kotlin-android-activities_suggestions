package ru.gubatenko.patterns

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.*
import com.example.navigation.AUTH_REQUEST_BROADCAST
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase
import java.util.concurrent.TimeUnit

class UploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val useCase: SyncActivitiesWithServerUseCase by inject()
    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(appContext) }

    override suspend fun doWork(): Result {
        return try {
            useCase.execute()
            Result.success()
        } catch (e: UnknownUserException) {
            localBroadcastManager.sendBroadcast(Intent(AUTH_REQUEST_BROADCAST))
            Result.failure()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

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