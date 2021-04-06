package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AuthViewModel

@Composable
fun HomeScreen() {
    val viewModel = viewModel<AuthViewModel>()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Welcome to home screen")
        Button(
            onClick = {
                viewModel.logout()
            }
        ) {
            Text("Log Out")
        }
    }
}