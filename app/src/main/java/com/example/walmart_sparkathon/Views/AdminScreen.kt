package com.example.walmart_sparkathon.Views

import android.net.Uri
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.walmart_sparkathon.Models.FileNameHolder
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.ViewModels.AdminScreenViewModel
import com.example.walmart_sparkathon.composables.TopBar
import com.example.walmart_sparkathon.ui.theme.GrayColor
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.OnSecondary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.TertiaryColor

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
    FileNameHolder.fileName = viewModel.fileName.collectAsState().value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar()
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(SecondaryColor)
                .padding(20.dp)
        ) {
            Row {
                Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Image(
                        painter = painterResource(R.drawable.maps_icon),
                        contentDescription = "Example Image", // Description for accessibility
                        modifier = Modifier.size(100.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(text = "Map Your Store", color = OnPrimary, fontSize = 20.sp)
                    Text(text = "within minutes",color = OnPrimary, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            // Display the selected image
            imageUri?.let {
                Image(
                    painter = rememberImagePainter(
                        data = it,
                        builder = {

                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(10.dp)
                )
            } ?: Column(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(0.9f)
                .background(Color.Gray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(R.drawable.demo_image),
                    contentDescription = "Example Image", // Description for accessibility
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                // Button to pick an image
                Button(
                    onClick = { imagePickerLauncher.launch("image/*") },
                    colors = ButtonColors(
                        containerColor = TertiaryColor,
                        contentColor = OnPrimary,
                        disabledContentColor = OnPrimary,
                        disabledContainerColor = PrimaryColor
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Pick an Image", fontSize = 18.sp, modifier = Modifier.padding(12.dp))
                }
                Button(onClick = { viewModel.send_walmart_image(localContext, imageUri) },
                    colors = ButtonColors(
                        containerColor = TertiaryColor,
                        contentColor = OnPrimary,
                        disabledContentColor = OnPrimary,
                        disabledContainerColor = PrimaryColor
                    ),
                    shape = RoundedCornerShape(20.dp)) {
                    Text("Add Image", fontSize = 18.sp, modifier = Modifier.padding(12.dp))
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(0.93f),) {
            Button(onClick = { navController.navigate("grid_screen") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = TertiaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(20.dp)) {
                Text("Mark your map", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
                Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
