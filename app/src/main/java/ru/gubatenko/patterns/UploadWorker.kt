package ru.gubatenko.patterns

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.gubatenko.domain.AUTH_REQUEST_BROADCAST
import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase

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
            Log.d(this.javaClass.simpleName, e.message ?: "")
            localBroadcastManager.sendBroadcast(Intent(AUTH_REQUEST_BROADCAST))
            Result.failure()
        } catch (e: Exception) {
            Log.d(this.javaClass.simpleName, e.message ?: "")
            Result.retry()
        }
    }
}