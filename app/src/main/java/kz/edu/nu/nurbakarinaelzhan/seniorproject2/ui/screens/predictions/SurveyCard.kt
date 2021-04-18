package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.predictions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.network.SurveyStatus
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.DarkBlue
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.theme.MintLeaf

@Composable
fun SurveyCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    survey: SurveyStatus?
) {
    val valid = survey != null
    Card(
        modifier = modifier
            .fillMaxWidth(),
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
                        if(valid) navController.navigate("survey?readonly=true")
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