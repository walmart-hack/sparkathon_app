package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.ViewModels.GridScreenViewModel
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.OnSecondary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor

@Composable
fun ImageTapScreen(navController: NavController, viewModel: GridScreenViewModel = viewModel()) {
    var tappedOffset by remember { mutableStateOf<Offset?>(null) }
    var imageSize by remember { mutableStateOf(IntSize(0, 0)) }
    var containerSize by remember { mutableStateOf(IntSize(0, 0)) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }

    // Define the list of categories
    val categories = listOf(
        "Animals & Pet Supplies",
        "Apparel & Accessories",
        "Arts & Entertainment",
        "Baby & Toddler",
        "Business & Industrial",
        "Cameras & Optics",
        "Electronics",
        "Food, Beverages & Tobacco",
        "Furniture",
        "Hardware",
        "Health & Beauty",
        "Home & Garden",
        "Luggage & Bags",
        "Media",
        "Office Supplies",
        "Religious & Ceremonial",
        "Software",
        "Sporting Goods",
        "Toys & Games",
        "Vehicles & Parts"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Mark Locations", fontSize = 26.sp, color = OnPrimary)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .onGloballyPositioned { layoutCoordinates ->
                    containerSize = layoutCoordinates.size // Capture the size of the container
                }
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        // Calculate the relative position if the tap is within the image bounds
                        val relativeX = offset.x - (containerSize.width - imageSize.width) / 2f
                        val relativeY = offset.y - (containerSize.height - imageSize.height) / 2f
                        if (relativeX in 0f..imageSize.width.toFloat() && relativeY in 0f..imageSize.height.toFloat()) {
                            tappedOffset = Offset(relativeX, relativeY)
                            showCategoryDialog = true
                        } else {
                            tappedOffset = null // Ignore taps outside the image
                        }
                    }
                }
        ) {
            // Display the image
            Image(
                painter = painterResource(id = R.drawable.layout),
                contentDescription = "Tappable Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { layoutCoordinates ->
                        imageSize = layoutCoordinates.size // Capture the image size in pixels
                    }
            )

            // Show a dialog to select a category
            if (showCategoryDialog) {
                CategorySelectionDialog(
                    categories = categories,
                    onCategorySelected = { category ->
                        selectedCategory = category
                        tappedOffset?.let { offset ->
                            viewModel.addCategory(
                                xCoordinate = offset.x.toInt(),
                                yCoordinate = offset.y.toInt(),
                                categoryName = selectedCategory
                            )
                        }
                        showCategoryDialog = false
                    },
                    onDismiss = { showCategoryDialog = false }
                )
            }

            // Display tapped coordinates relative to the image
            tappedOffset?.let { offset ->
                Text(
                    text = "",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White.copy(alpha = 0.7f))
                )
            }
        }

        // Submit button at the bottom
        Button(
            onClick = {
                viewModel.submitCategory()
                navController.navigate("admin_success")
            },
            modifier = Modifier
                .padding(16.dp)
            ,
            colors = ButtonColors(
                containerColor = SecondaryColor,
                contentColor = OnSecondary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            )
        ) {
            Text("Submit")
        }
    }
}



@Composable
fun CategorySelectionDialog(
    categories: List<String>,
    onCategorySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Category") },
        text = {
            // Use LazyColumn for a scrollable list
            LazyColumn {
                items(categories) { category ->
                    Text(
                        text = category,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                onCategorySelected(category)
                                onDismiss() // Close the dialog after selection
                            }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
