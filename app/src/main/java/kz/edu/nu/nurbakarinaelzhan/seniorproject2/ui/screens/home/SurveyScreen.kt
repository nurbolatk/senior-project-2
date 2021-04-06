package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SurveyScreen() {
    Row() {
        StaggeredGrid() {
            for (topic in topics) {
                Chip(modifier = Modifier.padding(8.dp), text = topic)
            }
        }
    }
}



@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Keep track of the max height of each row
        val rowMaxHeights = mutableListOf<Int>()

        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        var rows = 1
        var currentRowWidth = 0
        rowMaxHeights.add(0)

        val placeables = measurables.mapIndexed { index, measurable ->
            // Measure each child
            val placeable = measurable.measure(constraints)

            // Track the width and max height of each row
            if(currentRowWidth + placeable.width < constraints.maxWidth) {

                currentRowWidth += placeable.width
            } else {
                rows++
                rowMaxHeights.add(0)
                currentRowWidth = placeable.width
            }
            // Track the width and max height of each row
            rowMaxHeights[rows-1] = kotlin.math.max(rowMaxHeights[rows-1], placeable.height)

            placeable
        }

        // Grid's height is the sum of the tallest element of each row
        // coerced to the height constraints
        val height = rowMaxHeights.sumBy { it }
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // Y of each row, based on the height accumulation of previous rows
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i-1] + rowMaxHeights[i-1]
        }

        // Set the size of the parent layout
        layout(constraints.maxWidth, height) {
            // x cord we have placed up to, per row
            val rowX = IntArray(rows) { 0 }

            var row = 0
            placeables.forEachIndexed { index, placeable ->
                if(rowX[row] + placeable.width < constraints.maxWidth) {
                    placeable.placeRelative(
                        x = rowX[row],
                        y = rowY[row]
                    )
                    rowX[row] += placeable.width
                } else {
                    row++
                    placeable.placeRelative(
                        x = rowX[row],
                        y = rowY[row]
                    )
                    rowX[row] += placeable.width
                }


            }
        }
    }
}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)