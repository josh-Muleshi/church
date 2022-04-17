package cd.wayupdev.church.ui.screen.home


import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    BackHandler(enabled = true) {
        (context as? Activity)?.finish()
    }
    Scaffold(
        topBar = {
            //TopPageBar(navController, user = Data)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}, backgroundColor = MaterialTheme.colors.primary){
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }}
    ){
        Text(text = "josh Muleshi",modifier = Modifier.padding(16.dp), color = Color.Black)
    }
}