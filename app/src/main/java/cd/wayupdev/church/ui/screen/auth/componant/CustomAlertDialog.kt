package cd.wayupdev.church.ui.screen.auth.componant

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cd.wayupdev.church.app.navigation.Screen
import cd.wayupdev.church.ui.screen.auth.business.AuthState

@Composable
fun CustomAlertDialog(state: AuthState,navController: NavHostController){

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    val buttonColor by animateColorAsState(
        targetValue = Color.Red,
        animationSpec = tween(durationMillis = 1000)
    )

    if (openDialog.value)
    {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {

                AlertButton(isLoading = state.isLoading, buttonColor = buttonColor) { isLoading ->
                    state.isLoading
                } },
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
                        navController.navigate(Screen.Home.route)
                    }) {
                    Text(text = "cancel", color = MaterialTheme.colors.primary, fontSize = 16.sp,)
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.White
        )
    }
}

@Composable
fun AlertButton(isLoading: Boolean, buttonColor: Color, onClick : (isLoading: Boolean) -> Unit) {

    val infiniteTransition = rememberInfiniteTransition()
    val buttonRadius by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val shadowRadius by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val slowShadowRadius by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(2100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {

        Surface(shape = CircleShape,modifier = Modifier
            .size(150.dp * shadowRadius)
            .align(Alignment.Center), color = buttonColor.copy(alpha = 0.2f), content = {})

        Surface(shape = CircleShape,modifier = Modifier
            .size(120.dp * slowShadowRadius)
            .align(Alignment.Center), color = buttonColor.copy(alpha = 0.2f), content = {})

        Surface(shape = CircleShape,modifier = Modifier
            .size(100.dp * slowShadowRadius)
            .align(Alignment.Center), color = buttonColor.copy(alpha = 0.2f), content = {})

        Surface(color = buttonColor,shape = CircleShape,modifier = Modifier
            .size(150.dp / buttonRadius)
            .align(Alignment.Center)
            .clickable {
                onClick.invoke(isLoading)
            } ) {

            Icon(imageVector = Icons.Rounded.Warning, contentDescription = null, tint = Color.White, modifier = Modifier
                .fillMaxSize()
                .padding(24.dp))
        }
    }
}