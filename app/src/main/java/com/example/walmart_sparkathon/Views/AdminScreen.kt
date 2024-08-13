package com.example.walmart_sparkathon.Views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.walmart_sparkathon.ViewModels.AdminScreenViewModel

@Composable
fun AdminScreen(navController: NavController, viewModel: AdminScreenViewModel = viewModel()) {
    // Create a launcher to handle the result of the image picker intent
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { selectedImageUri ->
            // Update the state with the selected image URI
            viewModel.updateImageUri(selectedImageUri)
        }
    }

    // Retrieve the image URI from the ViewModel
    val imageUri by viewModel.selectedImageUri.collectAsState()
    val fileName by viewModel.fileName.collectAsState()
    val width by viewModel.width.collectAsState()
    val height by viewModel.height.collectAsState()
    val localContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Button to pick an image
        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text("Pick an Image")
        }
        Button(onClick = { viewModel.send_walmart_image(localContext, imageUri) }) {
            Text("Continue")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the selected image
        imageUri?.let {
            Image(
                painter = rememberImagePainter(
                    data = it,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        } ?: Text("No image selected", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Display additional information
        Text(text = "File Name: ${fileName ?: "No file name"}", fontSize = 18.sp)
        Text(text = "Width: ${width?.toString() ?: "No width"}", fontSize = 18.sp)
        Text(text = "Height: ${height?.toString() ?: "No height"}", fontSize = 18.sp)
    }
}
