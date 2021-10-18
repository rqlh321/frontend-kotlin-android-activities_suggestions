package com.example.domain_android.usecase

import android.content.Context
import androidx.work.WorkManager
import ru.gubatenko.domain.usecase.LongTermWorkUseCase
import com.example.domain_android.work.DownloadWorker.Companion.runDownloadWorker
import com.example.domain_android.work.UploadWorker.Companion.runUploadWorker

class LongTermWorkUseCaseImpl(
    private val context: Context
) : LongTermWorkUseCase {

    companion object{
        const val SYNC_JOB_TAG = "SYNC_JOB_TAG"
    }
    override fun execute(query: LongTermWorkUseCase.Query) {
        when (query) {
            is LongTermWorkUseCase.Query.StartUpload -> {
                context.runUploadWorker()
            }
            is LongTermWorkUseCase.Query.StartDownload -> {
                context.runDownloadWorker()
            }
            is LongTermWorkUseCase.Query.StopLoad -> {
                WorkManager.getInstance(context).cancelAllWorkByTag(SYNC_JOB_TAG)
            }
        }
    }
}