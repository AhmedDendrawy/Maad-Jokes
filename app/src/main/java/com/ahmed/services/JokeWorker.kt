package com.ahmed.services

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class JokeWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        showNotification(applicationContext)
        return Result.success()
    }
}

fun scheduleDailyJoke(context: Context) {

    val dailyWorkRequest = PeriodicWorkRequestBuilder<JokeWorker>(
        15,
        TimeUnit.MINUTES
    )
        .build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "DailyJoke",
        ExistingPeriodicWorkPolicy.KEEP,
        dailyWorkRequest
    )
}

/*Test*/

//fun scheduleDailyJoke(context: Context) {
//
//    val testWorkRequest = OneTimeWorkRequestBuilder<JokeWorker>()
//        .setInitialDelay(15, TimeUnit.SECONDS)
//        .build()
//
//    WorkManager.getInstance(context).enqueueUniqueWork(
//        "TestJoke",
//        ExistingWorkPolicy.REPLACE,
//        testWorkRequest
//    )
//}