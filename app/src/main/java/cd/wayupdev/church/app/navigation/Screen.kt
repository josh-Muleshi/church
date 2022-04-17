package cd.wayupdev.church.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String = "", val icon: ImageVector? = null){
    object MainNavHost : Screen(route = "main_navhost")
    object Splash : Screen("splash")
    object Home : Screen("home", "Accueil", Icons.Default.Home)
    object Search : Screen("Search", "Rechercher" , Icons.Default.Search)
    object Notifications : Screen("Notifications", "Notification",Icons.Default.Notifications)
    object Message : Screen("Message", "Message", Icons.Default.Send)
    object PostDetail : Screen("postDetail")
    object Profile : Screen("profile")
    object EditProfile : Screen("editProfile")
    object Setting : Screen("setting")
    object Login : Screen("login")
}

fun getBottomNavItems(): List<Screen> {
    return listOf(Screen.Home, Screen.Search, Screen.Notifications, Screen.Message)
}

