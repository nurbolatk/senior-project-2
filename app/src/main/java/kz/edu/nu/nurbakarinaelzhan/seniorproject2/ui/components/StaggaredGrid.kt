package kz.edu.nu.nurbakarinaelzhan.seniorproject2.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import kotlin.math.max


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
            rowMaxHeights[rows-1] = max(rowMaxHeights[rows-1], placeable.height)

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
