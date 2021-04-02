package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AuthViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.LoginScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.auth.RegisterScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home.AppWrapper
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.SeniorProject2Theme

@Composable
fun MyApp(content: @Composable () -> Unit) {
    SeniorProject2Theme {
        content()
    }
}

@Composable
fun MainNavController(viewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController, if(viewModel.currentUser == null) "login" else "app") {
        composable("login") { LoginScreen(viewModel, navController) }
        composable("register") { RegisterScreen(viewModel, navController) }
        composable("app") { AppWrapper(viewModel) }
    }
}

