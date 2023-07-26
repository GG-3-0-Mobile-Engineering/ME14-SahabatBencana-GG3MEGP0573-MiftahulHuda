package com.huda.sahabatbanjir.notification

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkManagerHandler(context: Context) {

    private val workManager = WorkManager.getInstance(context)

    fun scheduleNotificationWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        Log.d("scheduleNotificationWorker", "START")

        val notificationRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            1, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            NOTIFICATION_WORKER_TAG,
            ExistingPeriodicWorkPolicy.KEEP,
            notificationRequest
        )
    }

    fun cancelNotificationWorker() {
        Log.d("scheduleNotificationWorker", "END")
        workManager.cancelUniqueWork(NOTIFICATION_WORKER_TAG)
    }

    private companion object {
        const val NOTIFICATION_WORKER_TAG = "notification_worker_tag"
    }
}