package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel

@Composable
fun PredictionStatusScreen(navController: NavHostController, viewModel: AppViewModel) {
    viewModel.fetchStatus()
    Column() {
        Text("Hi there")
        Button(onClick = {
            navController.navigate("survey")
        }) {
            Text("Take a survey")
        }
    }
}