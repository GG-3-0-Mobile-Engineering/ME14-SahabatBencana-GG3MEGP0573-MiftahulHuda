package com.huda.sahabatbanjir.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.miftahulhudaf.sahabatbencana.MainActivity
import com.miftahulhudaf.sahabatbencana.R
import com.miftahulhudaf.sahabatbencana.data.repository.MainRepository
import com.miftahulhudaf.sahabatbencana.data.response.monitoring.GeometriesItem
import com.miftahulhudaf.sahabatbencana.data.utils.Resource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.single
import org.koin.java.KoinJavaComponent.inject
import retrofit2.HttpException

class NotificationWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    private val repository by inject<MainRepository>(MainRepository::class.java)

    override suspend fun doWork(): Result = coroutineScope {
        try {
            val result = repository.getMonitoring().single()

            if (result is Resource.Success) {
                val geometries = result.data!!

                if (geometries.isNotEmpty()) {
                    showNotification(geometries)
                    Result.success()
                } else {
                    Result.failure()
                }
            } else if (result is Resource.Error) {
                Result.failure()
            } else {
                Result.failure()
            }
        } catch (e: HttpException) {
            Log.e(TAG, "getMonitoring: ${e.message()}")
            Result.failure()
        }
    }

    private fun showNotification(geometries: List<GeometriesItem>) {
        val notificationStyle = NotificationCompat.InboxStyle()
        geometries.forEach {
            notificationStyle.addLine(it.properties.gaugeNameId)
        }

        val notificationIntent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_REQUEST_CODE,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setContentTitle(applicationContext.getString(R.string.notification_title))
                .setContentText(applicationContext.getString(R.string.notification_content))
                .setSmallIcon(R.drawable.ic_notifications)
                .setStyle(notificationStyle)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                applicationContext.getString(R.string.pref_notify_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = notificationBuilder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private companion object {
        const val NOTIFICATION_REQUEST_CODE = 101
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_CHANNEL_ID = "disaster_notification_channel"
        const val TAG = "NotificationWorker"
    }
}