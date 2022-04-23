package cd.wayupdev.church.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.app.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 4000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(5000)
            navController.navigate(Screen.MainNavHost.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(modifier = Modifier
        .background(if (isSystemInDarkTheme()) Color.Black else MaterialTheme.colors.background)
        .fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(170.dp)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.ic_bible),
            contentDescription = "app logo"
        )

        Column(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "from",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .alpha(alpha = alpha)
            )
            Image(
                modifier = Modifier
                    .padding(4.dp)
                    .width(70.dp)
                    .alpha(alpha = alpha),
                painter = painterResource(id = R.drawable.ic_webox),
                contentDescription = "webox"
            )
        }
    }
}