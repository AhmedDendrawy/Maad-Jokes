package com.ahmed.maadjokes.ui

import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.notifications

@Composable
fun PermissionDeniedDialog(modifier: Modifier = Modifier, onMyClick: () -> Unit) {
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                onClick = {
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                    context.startActivity(intent)
                    onMyClick()
                }
            ) {
                Text("Allow")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onMyClick
            ) {
                Text("Cancel")
            }
        },
        title = { Text("Feature Unavailable ") },
        text = { Text("Without notification permission, you won’t receive jokes from this app. To stay happy, please enable notifications in Settings.") },
        icon = {
            Icon(
                imageVector = notifications,
                contentDescription = "Notification icon",
            )
        }
    )
}
@Preview(showSystemUi = true)
@Composable
private fun PermissionDeniedDialogPreview() {
    PermissionDeniedDialog() {}
}