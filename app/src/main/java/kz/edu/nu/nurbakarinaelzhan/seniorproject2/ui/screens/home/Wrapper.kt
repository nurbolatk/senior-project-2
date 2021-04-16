package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.HomeScreen
import timber.log.Timber

@Composable
fun AppWrapper(higherNavController: NavHostController, viewModel: AppViewModel = viewModel()) {

    val currentUser by viewModel.currentUser.observeAsState()
    Timber.d(if (currentUser == null) "current user null" else "currentuser ${currentUser!!.email}")
    if (currentUser == null) {
        higherNavController.navigate("login") {
            popUpTo("app") { inclusive = true }
        }
    }
    HomeScreen(higherNavController, viewModel)
}

