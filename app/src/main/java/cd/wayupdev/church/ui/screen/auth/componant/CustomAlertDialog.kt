package cd.wayupdev.church.ui.screen.auth.componant

import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.app.navigation.Screen

@Composable
fun CustomAlertDialog(navController: NavHostController){

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value)
    {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                Image(
                    painter = painterResource(id = R.drawable.ic_alt),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .size(200.dp),
                    alignment = Alignment.TopCenter
                ) },
            text = { Text(
                text = "Only for admin please",
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        Toast.makeText(context, "authentication", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "ok", color = MaterialTheme.colors.primary, fontSize = 16.sp,)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        navController.navigate(Screen.About.route)
                    }) {
                    Text(text = "cancel", color = MaterialTheme.colors.primary, fontSize = 16.sp,)
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.White
        )
    }
}