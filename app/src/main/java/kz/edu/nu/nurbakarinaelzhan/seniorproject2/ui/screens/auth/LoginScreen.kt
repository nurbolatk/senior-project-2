package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.UserCredentials
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.EmailField
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.PasswordField
import timber.log.Timber

@Composable
fun LoginScreen(navController: NavController, viewModel:AppViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

//    if(viewModel.status == ApiStatus.ERROR) {
//        Toast.makeText(LocalContext.current, "Error signing in", Toast.LENGTH_SHORT).show()
//        viewModel.status = ApiStatus.INIT
//    }

    val currentUser by viewModel.currentUser.observeAsState()

    Timber.d(if(currentUser == null) "current user null" else "currentuser ${currentUser!!.email}")
    if(currentUser != null) {
        navController.navigate("app") {
            popUpTo("login") {
                inclusive = true
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Please, log in", style = typography.h4)
            Spacer(Modifier.height(24.dp))
            EmailField(email, setEmail)
            Spacer(Modifier.height(8.dp))
            PasswordField(password, setPassword)
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                val credentials = UserCredentials(
                    email, password
                )
                viewModel.login(credentials)
            }) {
                Text("Log in")
            }

            Spacer(Modifier.height((64.dp)))
            Text(
                "Create account",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
//    LoginScreen(, rememberNavController())
}
