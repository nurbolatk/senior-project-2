package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.R
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.auth.AppViewModel
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.db.DBPrediction
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components.TextButton
import java.text.SimpleDateFormat

@Composable
fun PredictionsList(predictions: List<DBPrediction>) {
    val scrollState = rememberLazyListState()
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")

    LazyColumn(state = scrollState) {
        items(predictions) { prediction->
            Row(
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    if(prediction.value == 1)
                        "You got positive result"
                    else
                        "You got negative result"
                )
                Text(
                    simpleDateFormat.format(prediction.createdAt).toString(),
                    style = typography.caption
                )
            }
        }
    }
}

@Composable
fun Predictions(viewModel: AppViewModel) {
    val predictionsState = viewModel.predictions.observeAsState()
    val predictions = predictionsState.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(predictions == null) {
            Text("Loading")
        } else {
            if(predictions.isNotEmpty()) {
                PredictionsList(predictions)
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_pixeltrue_seo_1),
                    contentDescription = null,
                )
                Text(text = "No predictions made yet")
            }
        }
        TextButton(
            text = "Refresh",
            onClick = {
                viewModel.fetchPrediction()
            }
        )
    }
}