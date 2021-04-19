package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.StaggeredGrid
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.Grey
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.MintLeaf
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

@Composable
fun PredictionDetailsScreen(viewModel: AppViewModel, id: Long?) {
    id?.let {
        viewModel.getPredictionById(it)
        StatefulPredictionDetailsScreen(viewModel = viewModel)
    }
}

@Composable
fun StatefulPredictionDetailsScreen(viewModel: AppViewModel) {
    val predictionState = viewModel.prediction.observeAsState()
    val prediction = predictionState.value
    val simpleDateFormat = SimpleDateFormat("EEE, MMM dd, HH:mm")
    val listState = rememberLazyListState()
    val survey = mutableMapOf<String, Int>()

    prediction?.let {
        survey["sputum"] = it.sputum ?: 0
        survey["muscle_pain"] = it.muscle_pain ?: 0
        survey["sore_throat"] = it.sore_throat ?: 0
        survey["cold"] = it.cold ?: 0
        survey["sneeze"] = it.sneeze ?: 0
        survey["reflux"] = it.reflux ?: 0
        survey["diarrhea"] = it.diarrhea ?: 0
        survey["runny_nose"] = it.runny_nose ?: 0
        survey["chest_pain"] = it.chest_pain ?: 0
        survey["cough"] = it.cough ?: 0
        survey["joint_pain"] = it.joint_pain ?: 0
        survey["flu"] = it.flu ?: 0
        survey["headache"] = it.headache ?: 0
        survey["vomiting"] = it.vomiting ?: 0
        survey["loss_appetite"] = it.loss_appetite ?: 0
        survey["chills"] = it.chills ?: 0
        survey["nausea"] = it.nausea ?: 0
        survey["physical_discomfort"] = it.physical_discomfort ?: 0
        survey["abdominal_pain"] = it.abdominal_pain ?: 0

        val positive = it.covid_value == 1
        val chance = it.covid_percents?.roundToInt() ?: 0
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Column(
                    Modifier
                        .padding(top = 24.dp, bottom = 16.dp, start = 8.dp, end = 8.dp)
                ) {

                    Text("Prediction #${it.id}", style = typography.h4)
                    Text(
                        simpleDateFormat.format(prediction.createdAt).toString(),
                        modifier = Modifier.padding(top = 4.dp),
                        style = typography.body1,
                        color = Grey
                    )

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = if (positive)
                            "You might have COVID-19"
                        else
                            "You probably don't have COVID-19",
                        color = if (positive) Color.Red else MintLeaf,
                        style = typography.h4,
                        lineHeight = 40.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Probability of having COVID-19 - ${chance}%"
                    )

                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "Details",
                        style = typography.body1,
                        color = Grey
                    )
                    Spacer(Modifier.height(12.dp))
                    TempSensorCard(it.thermometer, it.fever_value, it.fever_percents)
                    Spacer(Modifier.height(12.dp))
                    Spo2SensorCard(
                        value = it.pulseoximeter,
                        symptom = it.fatigue_value,
                        symptomPercents = it.fatigue_percents
                    )
                    Spacer(Modifier.height(12.dp))
                    SpiroSensorCard(
                        value = it.spirometer,
                        symptom1 = it.difficult_breathing,
                        symptom2 = it.pneumonia
                    )

                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Survey",
                        style = typography.body1,
                        color = Grey
                    )
                    Spacer(Modifier.height(12.dp))

                    StaggeredGrid() {
                        for (topic in topics) {
                            survey[topic[0]]?.let {
                                Chip(
                                    modifier = Modifier.padding(8.dp),
                                    text = topic[1],
                                    onClick = {},
                                    state = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TempSensorCard(value: Double?, symptom: Int? = 0, symptomPercents: Double?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Text("Temperature - $value°C")
            Spacer(Modifier.height(6.dp))
            Text(
                if (symptom == 1) {
                    "Fever - detected with probability of $symptomPercents%"
                } else {
                    "Fever - not detected, probability is less than 50% ($symptomPercents%)"
                },
                style = typography.body2
            )
        }


    }
}

@Composable
fun Spo2SensorCard(value: Int?, symptom: Int? = 0, symptomPercents: Double?) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Text("Pulse oximetry - $value°C")
            Spacer(Modifier.height(6.dp))
            Text(
                if (symptom == 1) {
                    "Fatigue - detected with probability of $symptomPercents%"
                } else {
                    "Fatigue - not detected, probability is less than 50% ($symptomPercents%)"
                },
                style = typography.body2
            )
        }

    }
}

@Composable
fun SpiroSensorCard(value: Double?, symptom1: Int? = 0, symptom2: Int? = 0) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Text("Spirometer (FEV1) - $value L/s")
            if (symptom1 == 1) {
                Spacer(Modifier.height(6.dp))
                Text(
                    "Difficult breathing - detected",
                    style = typography.body2
                )
            }
            if (symptom2 == 1) {
                Spacer(Modifier.height(6.dp))
                Text(
                    "Pneumonia - detected",
                    style = typography.body2
                )
            }
        }

    }
}