package cd.wayupdev.church.ui.screen.motifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import cd.wayupdev.church.R

@Composable
fun NotificationShow() {
    val context = LocalContext.current
    val channelId = "MakeitEasy"
    val notificationId = 0
    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_bible)
    val bigText = "Contrary to popular belief, lorem ipsum is not simply random text. It has roots in a piece of classical latin literature from 45 BC, making it over 2000 year old."

    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }

    Button(
        onClick = {
            bigPictureWithThumbnailNotification(
                context,
                channelId,
                notificationId,
                "Big Picture + Avatar Notification",
                "This is a notification showing big picture and an auto hiding avatar.",
                myBitmap
            )
        }
    ) {
        Text(
            text = "Big Picture + Big Icon Notification",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
    }
}

fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "MakeitEasy"
        val desc = "My Channel MakeitEasy"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = desc
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun bigPictureWithThumbnailNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    bigImage: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_alert)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigImage)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(bigImage)
                .bigLargeIcon(null)
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}