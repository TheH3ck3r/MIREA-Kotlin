package com.example.pr78

import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class SimpleWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("SimpleWorker", "Work is being executed")
        return Result.success()
    }
}

fun startWorkManagerTask() {
    val workRequest = OneTimeWorkRequestBuilder<SimpleWorker>()
        .setInitialDelay(5, TimeUnit.SECONDS)
        .build()

    WorkManager.getInstance().enqueue(workRequest)
}