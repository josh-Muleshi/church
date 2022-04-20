package cd.wayupdev.church.ui.screen.settings.componant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cd.wayupdev.church.R
import cd.wayupdev.church.app.navigation.Screen

@Composable
fun AppBarScreen(navController: NavHostController, ScreenName: String) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp
    ){
        Row(
            modifier = Modifier
                .padding(start = 2.dp, end = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                Icon(
                    painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = ""
                )
            }

            Text(
                text = ScreenName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}