package cd.wayupdev.church.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.ui.screen.settings.componant.AppBarScreen

@Composable
fun SettingScreen(navController: NavHostController) {
    val names : List<String> = listOf("Language", "Dark Mode", "Help")
    Scaffold(
        topBar = {
            AppBarScreen(navController, ScreenName = "Settings")
        }
    ){

        Column(modifier = Modifier.fillMaxSize()) {
            names.forEach { name ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = name, modifier = Modifier.padding(16.dp), color = Color.Black)
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_righ), contentDescription = null, modifier = Modifier.padding(10.dp))
                }
                Divider()
            }

        }

    }
}