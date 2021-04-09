package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.ApiStatus
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.StaggeredGrid
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.DarkBlue
import java.util.*


@Composable
fun SurveyScreen(viewModel: AppViewModel, navController: NavHostController) {
    val (survey, setSurvey) = remember { mutableStateOf(
        mutableMapOf<String, Int>(
            "sputum" to 0,
            "muscle_pain" to 0,
            "sore_throat" to 0,
            "pneumonia" to 0,
            "cold" to 0,
            "fever" to 0,
            "sneeze" to 0,
            "reflux" to 0,
            "diarrhea" to 0,
            "runny_nose" to 0,
            "difficult_breathing" to 0,
            "chest_pain" to 0,
            "cough" to 0,
            "joint_pain" to 0,
            "fatigue" to 0,
            "flu" to 0,
            "headache" to 0,
            "vomiting" to 0,
            "loss_appetite" to 0,
            "chills" to 0,
            "nausea" to 0,
            "physical_discomfort" to 0,
            "abdominal_pain" to 0,
        )
    )}

    val status = viewModel.symptomsStatus.observeAsState()
    if(status.value == ApiStatus.SUCCESS) {
        navController.popBackStack()
        viewModel.resetSymptomsStatus()
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 24.dp, bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text("Indicate symptoms you were experiencing lately", style = typography.h5)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Check all that apply", style = typography.body1)
            Spacer(modifier = Modifier.height(16.dp))
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            StaggeredGrid() {
                for (topic in topics) {
                    survey[topic[0]]?.let {
                        Chip(
                            modifier = Modifier.padding(8.dp),
                            text = topic[1],
                            onClick = {
                                val copy = mutableMapOf<String, Int>()
                                copy.putAll(survey)
                                if(it == 0) {
                                    copy[topic[0]] = 1
                                } else {
                                    copy[topic[0]] = 0
                                }
                                setSurvey(copy)
                            },
                            state = it
                        )
                    }
                }
            }
        }
        Button(
            onClick = {
              viewModel.sendSymptoms(survey)
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            enabled = status.value != ApiStatus.LOADING
        ) {
            Text("Submit".toUpperCase(Locale.ROOT))
        }
    }
}

val ChipStyles = hashMapOf<String, Any>(
    "filled" to hashMapOf(
        "bg" to DarkBlue,
        "color" to Color.White,
        "icon" to Icons.Outlined.TaskAlt
    ),
    "outlined" to hashMapOf(
        "bg" to Color.White,
        "color" to DarkBlue,
        "icon" to Icons.Outlined.RadioButtonUnchecked
    )
)

@Composable
fun Chip(modifier: Modifier = Modifier, text: String, onClick: () -> Unit, state: Int) {
    val styles: Map<*, *> = if (state == 1) ChipStyles["filled"] as Map<*, *> else ChipStyles["outlined"] as Map<*, *>
    val backgroundColor by animateColorAsState(styles["bg"] as Color)

    Card(
        modifier = modifier,
        border = BorderStroke(color = DarkBlue, width = 1.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .background(backgroundColor)
                .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                styles["icon"] as ImageVector,
                contentDescription = null,
                tint = styles["color"] as Color
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text, color = styles["color"] as Color)
        }
    }
}

val topics = listOf(
    listOf("sputum", "Sputum"),
    listOf("muscle_pain", "Muscle pain"),
    listOf("sore_throat", "Sore throat"),
    listOf("pneumonia", "Pneumonia"),
    listOf("cold", "Cold"),
    listOf("fever", "Fever"),
    listOf("sneeze", "Sneeze"),
    listOf("reflux", "Reflux"),
    listOf("diarrhea", "Diarrhea"),
    listOf("runny_nose", "Runny nose"),
    listOf("difficult_breathing", "Difficult breathing"),
    listOf("chest_pain", "Chest pain"),
    listOf("cough", "Cough"),
    listOf("joint_pain", "Joint pain"),
    listOf("fatigue", "Fatigue"),
    listOf("flu", "Flu"),
    listOf("headache", "Headache"),
    listOf("vomiting", "Vomiting"),
    listOf("loss_appetite", "Loss appetite"),
    listOf("chills", "Chills"),
    listOf("nausea", "Nausea"),
    listOf("physical_discomfort", "Physical discomfort"),
    listOf("abdominal_pain", "Abdominal pain"),
)