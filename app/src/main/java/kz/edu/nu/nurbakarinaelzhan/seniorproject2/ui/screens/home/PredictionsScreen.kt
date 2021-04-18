package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
fun PredictionsScreen(viewModel: AppViewModel) {
    viewModel.fetchPrediction()

    val predictionsState = viewModel.predictions.observeAsState()
    val predictions = predictionsState.value
    val scrollState = rememberLazyListState()

    Column (
        modifier = Modifier
            .scrollable(scrollState, Orientation.Vertical, true)
            .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(predictions == null) {
            Text("Loading")
        } else {
            if(predictions.isNotEmpty()) {
                PredictionsList(predictions)
            } else {
                Text("You need to provide 2 types of data to receive prediction", style = typography.h5)
                Text(
                    "Return to the previous screen to see what needs to be submitted",
                    style = typography.body1,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Spacer(Modifier.height(64.dp))
                Image(
                    painter = painterResource(R.drawable.ic_pixeltrue_seo_1),
                    contentDescription = null,
                )
                Text(text = "No predictions made yet. Please, check if you submitted all the data and try again later")
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