package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AuthViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.HomeScreen


sealed class Screen(val route: String, val name: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object History : Screen("history", "History", Icons.Filled.Timeline)
}

val items = listOf(
    Screen.Home,
    Screen.History,
)

@Composable
fun AppWrapper(viewModel: AuthViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.name) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo = navController.graph.startDestination
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                            }
                        }
                    )
                }

            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(8.dp).padding(innerPadding)) {
            HomeNavController(navController)
        }
    }
}


@Composable
fun HomeNavController(navController: NavHostController) {
    NavHost(navController, "home") {
        composable("home") { HomeScreen() }
        composable("history") { HistoryScreen() }
    }
}