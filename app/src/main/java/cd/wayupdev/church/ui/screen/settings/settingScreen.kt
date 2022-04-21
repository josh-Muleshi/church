package cd.wayupdev.church.ui.screen.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.app.navigation.Screen
import cd.wayupdev.church.ui.screen.settings.componant.AppBarScreen

@Composable
fun SettingScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBarScreen(navController, ScreenName = "Settings")
        }
    ){
        SettingItem{ item ->
            when(item){
                0 -> navController.navigate(Screen.About.route)
                2 -> navController.navigate(Screen.About.route)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingItem(selectedItem: (Int)->Unit) {
    val names : List<String> = listOf("Language", "Dark Mode", "About", "Help")
    LazyColumn {
        itemsIndexed(names){ index, name ->
            Row(
                modifier = Modifier.fillMaxWidth().clickable { selectedItem(index) },
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