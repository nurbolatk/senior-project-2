package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.HighlightOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.DarkBlue

fun <T> checkIfAllNull(vararg args: T): Boolean {
    for(t in args) {
        if(t == null) {
            return false
        }
    }
    return true
}

@Composable
fun PredictionStatusScreen(navController: NavHostController, viewModel: AppViewModel) {
    viewModel.fetchStatus()
    val predictionStatus = viewModel.predictionStatus
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        Text("You need to provide 2 types of data", style = MaterialTheme.typography.h5)
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            border = BorderStroke(color = DarkBlue, width = 1.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            val spo2 = predictionStatus.value?.sensors?.pulseoximeter
            val thermometer = predictionStatus.value?.sensors?.thermometer
            val spirometer = predictionStatus.value?.sensors?.spirometer
            val sensorsReceived = checkIfAllNull(
                spo2,
                thermometer,
                spirometer
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Column {
                    Text("Measurements from physical sensors")
                    Text(
                        if(sensorsReceived)
                            "All values from the sensors were received"
                        else
                            "Not all values were received. Please, measure these values using the sensors we provided",
                        style = MaterialTheme.typography.caption
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if(spo2 != null) {
                                Icons.Outlined.Done
                            } else {
                                Icons.Outlined.HighlightOff
                            },
                            contentDescription = null,
                            tint = if(spo2 != null)
                                MaterialTheme.colors.secondary
                            else
                                MaterialTheme.colors.primaryVariant
                        )
                        Text(
                            text = "SPO2",
                            modifier = Modifier
                                .padding(start = 8.dp),
                            color = if(spo2 != null)
                                MaterialTheme.colors.secondary
                            else
                                MaterialTheme.colors.primaryVariant
                        )
                        if(spo2?.fatigue?.value == 1) {
                            Text(
                                text = "There is ${spo2.fatigue.percents}% chance that you have fatigue",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if(thermometer != null) {
                                Icons.Outlined.Done
                            } else {
                                Icons.Outlined.HighlightOff
                            },
                            contentDescription = null,
                            tint = if(thermometer != null)
                                MaterialTheme.colors.secondary
                            else
                                MaterialTheme.colors.primaryVariant
                        )
                        Text(
                            text = "Thermometer",
                            modifier = Modifier
                                .padding(start = 8.dp),
                            color = if(thermometer != null)
                                MaterialTheme.colors.secondary
                            else
                                MaterialTheme.colors.primaryVariant
                        )
                        if(thermometer?.fever?.value == 1) {
                            Text(
                                text = "There is ${thermometer.fever.percents}% chance that you have fever",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            if(spirometer != null) {
                                Icons.Outlined.Done
                            } else {
                                Icons.Outlined.HighlightOff
                            },
                            contentDescription = null,
                            tint = if(spirometer != null)
                                MaterialTheme.colors.secondary
                            else
                                MaterialTheme.colors.primaryVariant
                        )
                        Text(
                            text = "Spirometer",
                            modifier = Modifier
                                .padding(start = 8.dp),
                            color = if(spirometer != null)
                                MaterialTheme.colors.secondary
                            else
                                MaterialTheme.colors.primaryVariant
                        )
                        if(spirometer?.pneumonia == 1) {
                            Text(
                                text = "You might have pneumonia",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        if(spirometer?.difficult_breathing == 1) {
                            Text(
                                text = "You might have difficulty in breathing",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            border = BorderStroke(color = DarkBlue, width = 1.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            val survey = predictionStatus.value?.survey

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,

            ) {
                Column {
                    Text(text = "Survey")
                    if(survey == null) {
                        Text(
                            "You need to take a survey on symptoms you are experiencing",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    } else {
                        Text(
                            "Survey is done",
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    if(survey == null) {
                        TextButton(
                            onClick = { navController.navigate("survey") },
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "Take a survey",
                                modifier = Modifier.padding(end = 6.dp))
                                Icon(
                                    Icons.Filled.East,
                                    contentDescription = null
                                )
                            }

                        }
                    }
                }
            }
        }

    }
}