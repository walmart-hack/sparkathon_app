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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.walmart_sparkathon.ViewModels.AdminScreenViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import java.io.File

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
        Button(onClick = {viewModel.send_walmart_image(imageUri)}) {
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
    }
}

