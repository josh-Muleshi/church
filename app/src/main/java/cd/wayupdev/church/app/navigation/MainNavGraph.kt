package cd.wayupdev.church.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cd.wayupdev.church.ui.screen.about.AboutScreen
import cd.wayupdev.church.ui.screen.addpost.AddPostScreen
import cd.wayupdev.church.ui.screen.addpost.business.AddPostState
import cd.wayupdev.church.ui.screen.auth.AuthScreen
import cd.wayupdev.church.ui.screen.favorite.FavoriteScreen
import cd.wayupdev.church.ui.screen.home.HomeScreen
import cd.wayupdev.church.ui.screen.message.MessageScreen
import cd.wayupdev.church.ui.screen.motifications.NotificationScreen
import cd.wayupdev.church.ui.screen.settings.SettingScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                getBottomNavItems().forEach { screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = { navController.navigate(screen.route) },
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = Color.Gray,
                        icon = {
                            Icon(imageVector = screen.icon as ImageVector, contentDescription = null)
                        }
                    )
                }
            }
        },
        content = { contentPadding ->
            NavHost(modifier = Modifier.padding(contentPadding), navController = navController, startDestination = Screen.Home.route) {
                composable(route = Screen.Home.route) {
                    HomeScreen()
                }
                composable(route = Screen.Favorite.route) {
                    FavoriteScreen()
                }
                composable(route = Screen.Notifications.route) {
                    NotificationScreen()
                }
                composable(route = Screen.Message.route) {
                    MessageScreen()
                }
                composable(route = Screen.Setting.route) {
                    SettingScreen(navController)
                }
                composable(route = Screen.About.route) {
                    AboutScreen(navController)
                }
                composable(route = Screen.Auth.route) {
                    AuthScreen(navController)
                }
                composable(route = Screen.AddPost.route) {
                    AddPostScreen(navController)
                }
            }
        }
    )
}