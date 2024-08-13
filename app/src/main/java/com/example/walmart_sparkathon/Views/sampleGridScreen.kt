package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun dpToPx(dp: Dp): Float {
    val density = LocalDensity.current.density
    return dp.value * density
}

@Composable
fun GridScreen() {
    // Define your grid size and cell size
    val density = LocalDensity.current.density
    val cellWidth = 40.dp.value * density
    val cellHeight = 40.dp.value * density
    val numberOfColumns = 1200 / cellWidth.toInt()
    val numberOfRows = 800 / cellHeight.toInt()

    // State to hold tapped cell coordinates
    var tappedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    // Function to handle cell tap
    fun onCellTap(row: Int, column: Int) {
        tappedCell = Pair(row, column)
    }

    // Display tapped cell coordinates
    if (tappedCell != null) {
        Text(text = "Tapped Cell: Row ${tappedCell!!.first}, Column ${tappedCell!!.second}", fontSize = 20.sp)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(numberOfColumns), // Number of columns calculated
        modifier = Modifier.fillMaxSize()
    ) {
        items(numberOfColumns * numberOfRows) { index ->
            val row = index / numberOfColumns
            val column = index % numberOfColumns
            Box(
                modifier = Modifier
                    .size(40.dp, 40.dp)
                    .background(Color.Gray)
                    .clickable { onCellTap(row, column) }
            ) {
                Text(
                    text = "$row,$column",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGridScreen() {
        GridScreen()
}

