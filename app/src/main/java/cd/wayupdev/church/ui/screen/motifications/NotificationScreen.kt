package cd.wayupdev.church.ui.screen.motifications

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cd.wayupdev.church.ui.screen.topAppBar.AppBar

@Composable
fun NotificationScreen() {
    Scaffold(
        topBar = {
            AppBar("Notification")
        }
    ){
        Text(text = "Notification Screen",modifier = Modifier.padding(16.dp), color = Color.Black)
    }
}