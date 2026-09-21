package com.ahmed.maadjokes

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ahmed.maadjokes.ui.PermissionDeniedDialog
import com.ahmed.maadjokes.ui.theme.MaadJokesTheme
import com.ahmed.services.scheduleDailyJoke
import com.ahmed.services.showNotification

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaadJokesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JokeButton(modifier = Modifier.padding(innerPadding))
                }
            }
        }
        createNotificationChannel()
    }

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            "123",
            "Jokes",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "show Jokes"
        val manger = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manger.createNotificationChannel(channel)
    }
}

@Composable
fun JokeButton(modifier: Modifier = Modifier) {
    var isDialogShown by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (isDialogShown)
        PermissionDeniedDialog {
            isDialogShown = false
        }

    val handler = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showNotification(context)
            scheduleDailyJoke(context)
        } else {
            isDialogShown = true
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    handler.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    showNotification(context)
                    scheduleDailyJoke(context)

                }
            },

            ) {
            Text("Haha me")
        }
    }
}

//@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun JokeButtonPreview() {
    JokeButton()
}




