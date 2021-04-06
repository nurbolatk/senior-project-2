package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel

@Composable
fun HomeScreen(viewModel: AppViewModel) {
    val currentUser = viewModel.currentUser.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if(currentUser != null) "Welcome, ${currentUser.name}" else "Good bye!",
                style = typography.h5
            )
            IconButton(
                onClick = {
                    viewModel.logout()
                }
            ) {
                Icon(
                    Icons.Filled.Logout,
                    contentDescription = null
                )
            }
        }
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp)
//        ) {
            Button(

                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),

            ) {
                Text("Take a survey", style = typography.button)
            }
//        }
    }
}