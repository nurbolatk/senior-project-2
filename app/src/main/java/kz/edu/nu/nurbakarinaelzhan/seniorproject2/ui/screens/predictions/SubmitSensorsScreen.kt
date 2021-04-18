package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel

@Composable
fun SubmitSensorsScreen(viewModel: AppViewModel) {
    val (spo2, setSpo2) = remember { mutableStateOf("") }
    val (temperature, setTemperature) = remember { mutableStateOf("") }
    val (fev1, setFev1) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(top = 24.dp).padding(horizontal = 8.dp)
    ) {
        Text("Enter sensor measurements manually", style = typography.h5)
        Spacer(modifier = Modifier.height(24.dp))
        Text("SPO2:")
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = spo2,
                onValueChange = setSpo2,
                label = { Text("enter value between 0 and 100") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("%", style = typography.h5)
            Spacer(modifier = Modifier.width(6.dp))
            IconButton(onClick = { viewModel.submitSpo2(spo2) }) {
                Icon(
                    Icons.Rounded.Send,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Temperature:")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = temperature,
                onValueChange = setTemperature,
                label = { Text("enter value in celsius") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("°C", style = typography.h5)
            Spacer(modifier = Modifier.width(6.dp))
            IconButton(onClick = { viewModel.submitTemperature(temperature) }) {
                Icon(
                    Icons.Rounded.Send,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Spirometer FEV1:")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = fev1,
                onValueChange = setFev1,
                label = { Text("enter value in L/s") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("L/s", style = typography.h5)
            Spacer(modifier = Modifier.width(6.dp))
            IconButton(onClick = { viewModel.submitSpirometer(fev1) }) {
                Icon(
                    Icons.Rounded.Send,
                    contentDescription = null
                )
            }
        }
    }
}