package cd.wayupdev.church.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cd.wayupdev.church.app.navigation.Screen

@Composable
fun TopPageBar(navController: NavController) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ){
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Home",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )

            IconButton(onClick = { navController.navigate(Screen.Setting.route) }) {

                Image(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "settings",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }
}
