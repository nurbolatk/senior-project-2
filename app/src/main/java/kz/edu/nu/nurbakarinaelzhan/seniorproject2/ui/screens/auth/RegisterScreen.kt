package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.ApiStatus
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AuthViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.NewUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.EmailField
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.MaOwnRadioGroup
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.PasswordField
import java.util.*

val genders = listOf(
    "Male",
    "Female"
)

@Composable
fun RegisterScreen(viewModel: AuthViewModel, navController: NavController) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (name, setName) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (age, setAge) = remember {
        mutableStateOf("")
    }
    val (gender, setGender) = remember {
        mutableStateOf("")
    }
    if(viewModel.status == ApiStatus.ERROR) {
        Toast.makeText(LocalContext.current, "Error signing in", Toast.LENGTH_SHORT).show()
        viewModel.status = ApiStatus.INIT
    }
    if(viewModel.currentUser != null) {
        navController.navigate("app") {
            popUpTo("register") {
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
            Text("Registration", style = typography.h4)
            Spacer(Modifier.height(24.dp))
            TextField(
                value = name,
                onValueChange = setName,
                label = { Text("Full Name") }
            )
            EmailField(email, setEmail)
            Spacer(Modifier.height(8.dp))
            PasswordField(password, setPassword)
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = age,
                    onValueChange = setAge,
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                )
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 24.dp)
                ) {
                    Text("Gender")
                    Spacer(Modifier.height(6.dp))
                    MaOwnRadioGroup(
                        gender,
                        setGender,
                        genders
                    )
                }
            }
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                val user = NewUser(
                    name,
                    email,
                    password,
                    gender.toLowerCase(Locale.ROOT),
                    age.toInt(),
                )
                viewModel.register(user)
            }) {
                Text("Register")
            }
        }
    }
}


@Preview
@Composable
fun PreviewRegister() {
//    RegisterScreen(rememberNavController())
}