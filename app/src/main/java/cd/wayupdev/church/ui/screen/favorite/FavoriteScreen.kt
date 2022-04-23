package cd.wayupdev.church.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cd.wayupdev.church.R
import cd.wayupdev.church.ui.screen.topAppBar.AppBar


@Composable
fun FavoriteScreen() {
    Scaffold(
        topBar = {
            AppBar("Favorite ")
        }
    ){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Image(
                painter = painterResource(id = R.drawable.ic_no_data),
                contentDescription = "no data",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}