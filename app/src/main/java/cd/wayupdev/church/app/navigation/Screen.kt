package cd.wayupdev.church.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String = "", val icon: ImageVector? = null){
    object MainNavHost : Screen(route = "main_navhost")
    object Splash : Screen("splash")
    object Home : Screen("home", "Accueil", Icons.Default.Home)
    object Favorite : Screen("Favorite", "Favories" , Icons.Default.Favorite)
    object Notifications : Screen("Notifications", "Notification",Icons.Default.Notifications)
    object Message : Screen("Message", "Message", Icons.Default.Send)
    object PostDetail : Screen("postDetail")
    object About : Screen("aboutus")
    object Auth : Screen("auth")
    object AddPost : Screen("addPost")
    object Setting : Screen("setting")
    object Login : Screen("login")
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Favorite, Screen.Notifications, Screen.Message)
}

