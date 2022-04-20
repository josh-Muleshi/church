package cd.wayupdev.church.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.app.navigation.Screen
import cd.wayupdev.church.ui.screen.about.business.AboutState
import cd.wayupdev.church.ui.screen.about.business.AboutViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AboutScreen(navController: NavHostController,aboutViewModel: AboutViewModel = hiltViewModel()) {

    val state by aboutViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            //AppBarScreen(navController,ScreenName = "About us")
        }
    ){
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            Surface(modifier = Modifier
                .clip(CircleShape),
                onClick = {
                coroutineScope.launch {
                    when(state) {
                        is AboutState.Success -> {
                            if ((state as AboutState.Success).isAuth) {
                                navController.navigate(Screen.AddPost.route)
                            } else {
                                navController.navigate(Screen.Auth.route)
                            }
                        }
                        is AboutState.Error -> {
                            snackbarHostState.showSnackbar((state as AboutState.Error).errorMessage)
                        }
                        else -> {}
                    }
                }
            }){
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(0.dp, MaterialTheme.colors.surface, CircleShape)
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.version),
                modifier = Modifier.padding(4.dp),
                color = Color.Black
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = stringResource(id = R.string.developed),
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
            )
            Text(
                text = stringResource(id = R.string.developer),
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontSize = 20.sp,
            )

            Text(
                text = stringResource(id = R.string.email),
                modifier = Modifier.padding(4.dp),
                color = Color.Black,
                fontStyle = FontStyle.Italic
            )

        }
    }
}


