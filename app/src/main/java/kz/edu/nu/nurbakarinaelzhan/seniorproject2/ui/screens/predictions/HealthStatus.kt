package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.DarkBlue
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.MintLeaf
import timber.log.Timber
import kotlin.math.roundToInt

fun <T> checkIfAllNull(vararg args: T): Boolean {
    for (t in args) {
        if (t == null) {
            return false
        }
    }
    return true
}

@Composable
fun HealthStatus(
    navController: NavHostController,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.fetchStatus()

    val predictionStatus = viewModel.predictionStatus.observeAsState()
    Timber.d("aloha ${predictionStatus.value}")
    val spo2 = predictionStatus.value?.sensors?.pulseoximeter
    val thermometer = predictionStatus.value?.sensors?.thermometer
    val spirometer = predictionStatus.value?.sensors?.spirometer

    val allSensorsReceived = checkIfAllNull(
        spo2,
        thermometer,
        spirometer
    )

    Column(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize(),
            border = BorderStroke(
                color = if (allSensorsReceived) MintLeaf else DarkBlue,
                width = 1.dp
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {

            Column(
                modifier = Modifier
//                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Measurements from physical sensors",
                            style = MaterialTheme.typography.h6,
                            color = if (allSensorsReceived) MintLeaf else DarkBlue
                        )
                        if (allSensorsReceived) {
                            Spacer(modifier = Modifier.size(8.dp))
                            Icon(
                                Icons.Outlined.Done,
                                contentDescription = null,
                                tint = if (allSensorsReceived) MintLeaf else DarkBlue
                            )
                        }
                    }
                    Text(
                        if (allSensorsReceived)
                            "All values from the sensors were received"
                        else
                            "Not all values were received. Please, measure these values using the sensors we provided",
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    SensorRow(spo2 != null, "SPO2", "${spo2?.value}%")
                    if (spo2?.fatigue?.value == 1) {
                        Text(
                            text = "There is ${spo2.fatigue.percents?.roundToInt()}% chance that you have fatigue",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    SensorRow(thermometer != null, "Thermometer", "${thermometer?.value}°C")
                    if (thermometer?.fever?.value == 1) {
                        Text(
                            text = "There is ${thermometer.fever.percents?.roundToInt()}% chance that you have fever",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    SensorRow(spirometer != null, "Spirometer", "${spirometer?.value}L/s")
                    if (spirometer?.pneumonia == 1) {
                        Text(
                            text = "You might have pneumonia",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                    if (spirometer?.difficult_breathing == 1) {
                        Text(
                            text = "You might have difficulty in breathing",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {

                    TextButton(
                        onClick = {
                            navController.navigate("submit_sensors")
                        },
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Enter manually",
                                modifier = Modifier.padding(end = 6.dp)
                            )
                            Icon(
                                Icons.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        val survey = predictionStatus.value?.survey
        val valid = survey != null
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            border = BorderStroke(color = if (valid) MintLeaf else DarkBlue, width = 1.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,

                ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Survey",
                            style = MaterialTheme.typography.h6,
                            color = if (valid) MintLeaf else DarkBlue
                        )
                        if (valid) {
                            Spacer(modifier = Modifier.size(8.dp))
                            Icon(
                                Icons.Outlined.Done,
                                contentDescription = null,
                                tint = if (valid) MintLeaf else DarkBlue
                            )
                        }
                    }
                    if (valid) {
                        Text(
                            "Survey is done",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    } else {
                        Text(
                            "You need to take a survey on symptoms you are experiencing",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    TextButton(
                        onClick = {
                            if (valid) navController.navigate("survey?readonly=true")
                            else navController.navigate("survey")
                        },
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (valid) "View your survey" else "Take a survey",
                                modifier = Modifier.padding(end = 6.dp)
                            )
                            Icon(
                                Icons.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}
