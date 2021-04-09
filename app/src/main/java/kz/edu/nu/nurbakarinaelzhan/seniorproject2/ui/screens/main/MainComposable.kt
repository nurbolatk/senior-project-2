package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.LoginScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.auth.RegisterScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home.AppWrapper
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home.SurveyScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.SeniorProject2Theme

@Composable
fun MyApp(content: @Composable () -> Unit) {
    SeniorProject2Theme {
        content()
    }
}

@Composable
fun MainNavController() {
    val navController = rememberNavController()
    val viewModel: AppViewModel = hiltNavGraphViewModel()
    NavHost(navController, "login") {
        composable("login") {
            LoginScreen(navController, viewModel)
        }
        composable("register") {
            RegisterScreen(navController, viewModel)
        }
        composable("app") {
            AppWrapper(navController, viewModel)
        }
        composable("survey") {
            SurveyScreen(viewModel, navController)
        }
    }
}

