# Maad Jokes 🎭

A simple, interactive Android application built to experiment with and apply modern concepts in Android development. The app aims to bring a smile to developers and programmers by displaying customized jokes, featuring a background task that delivers a new joke daily via notifications.

## 🚀 Features

* **Modern User Interface:** Built entirely using **Jetpack Compose**, applying Declarative UI concepts.
* **Background Notifications:** Schedules a daily notification containing a random joke using **WorkManager**, ensuring the feature works seamlessly even when the app is closed.
* **Permission Handling:** Manages runtime notification permissions to support Android 13 and above (API 33+).
* **Flexible User Experience:** Displays a custom dialog to guide the user to system settings if they deny the required notification permissions.

## 🛠️ Technologies & Concepts

* **Primary Language:** Kotlin
* **UI Design:** Jetpack Compose
* **Background Task Management:** WorkManager (PeriodicWorkRequest)
* **Notification Building:** NotificationCompat & PendingIntent
* **Architectural Patterns:** Applied **State Hoisting** and **Unidirectional Data Flow (UDF)** concepts to keep the code clean, reusable, and UI components stateless.

## 📂 Basic Code Structure

* `MainActivity`: The main screen, app entry point, and the location where the Notification Channel is created.
* `JokeButton`: The core UI component responsible for requesting permissions and triggering the instant notification.
* `PermissionDeniedDialog`: An alert dialog that appears when the user denies notification permissions.
* `JokeWorker`: A class extending `Worker`, responsible for executing the background task of displaying the notification via WorkManager.

