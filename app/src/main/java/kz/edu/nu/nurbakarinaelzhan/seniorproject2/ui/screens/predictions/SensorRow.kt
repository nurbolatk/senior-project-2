package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.HighlightOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SensorRow(provided: Boolean, label: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if (provided) {
                Icons.Outlined.Done
            } else {
                Icons.Outlined.HighlightOff
            },
            contentDescription = null,
            tint = if (provided)
                MaterialTheme.colors.secondary
            else
                MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "${label}${if(provided) " - $value" else ""}",
            modifier = Modifier
                .padding(start = 8.dp),
            color = if (provided)
                MaterialTheme.colors.secondary
            else
                MaterialTheme.colors.primaryVariant
        )
    }
}