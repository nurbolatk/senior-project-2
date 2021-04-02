package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun PasswordField(password: String, setPassword: (String) -> Unit) {
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = setPassword,
        label = { Text("password") },
        trailingIcon = { IconButton(onClick = { setPasswordVisible(!passwordVisible) } ) {
            Icon(if(passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = null)
        } },
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EmailField(email: String, setEmail: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = setEmail,
        label = { Text("email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MaOwnRadioGroup(selected: String, setSelected: (String) -> Unit, options: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(opt in options) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected === opt,
                    onClick = { setSelected(opt) }
                )
                Text(
                    opt,
                    modifier = Modifier
                        .clickable { setSelected(opt) }
                        .padding(4.dp)
                )
            }
        }
    }
}