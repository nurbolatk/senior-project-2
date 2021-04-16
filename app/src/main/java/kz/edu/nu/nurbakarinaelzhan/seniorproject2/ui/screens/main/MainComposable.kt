package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.LoginScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.auth.RegisterScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home.AppWrapper
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home.SurveyScreen
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions.PredictionStatusScreen
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
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
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
            composable("prediction_status") {
                PredictionStatusScreen(navController, viewModel)
            }
            composable(
                "survey?readonly={readonly}",
                arguments = listOf(navArgument("readonly") { type = NavType.BoolType; defaultValue = false})
            ) { navBackStackEntry ->
                SurveyScreen(navController, viewModel, navBackStackEntry.arguments?.getBoolean("readonly"))
            }
        }
    }
}

