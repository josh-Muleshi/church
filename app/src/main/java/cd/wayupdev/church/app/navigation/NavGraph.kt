package cd.wayupdev.church.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cd.wayupdev.church.ui.screen.splash.AnimatedSplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(route = Screen.Splash.route){
            AnimatedSplashScreen(navController)
        }
        composable(route = Screen.MainNavHost.route){
            MainNavGraph()
        }
    }
}
