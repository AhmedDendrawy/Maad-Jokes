package com.ahmed.services

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.ahmed.maadjokes.R

@SuppressLint("MissingPermission")
fun showNotification(context: Context) {
    val jokes = arrayOf(
        "An Android Developer had a new child called him Kotlin with \"Koty\" as a nickname (Koty Koty Koo!)",
        "Developers found a Java Developer who was trying to \"drink\" the language.",
        "A hungry Android Developer was trying to make an order from a restaurant, so he ordered a large \"compose\" burger.",
        "An iOS developer wanted to make a \"Swift\" career."
    )
    val joke = jokes.random()
    val link = "https://developer.android.com/compose"
    val i = Intent(Intent.ACTION_VIEW, link.toUri())
    val pendingIntent = PendingIntent.getActivity(
        context,
        101,
        i,
        PendingIntent.FLAG_IMMUTABLE
    )

    val notification = NotificationCompat.Builder(context, "123")
        .setSmallIcon(R.drawable.ic_stat_name)
        .setContentTitle("Maad Jokes")
        .setAutoCancel(true)
        .setContentText(joke)
        .setStyle(NotificationCompat.BigTextStyle().bigText(joke))
        .setContentIntent(pendingIntent)
        .build()
    NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
}



