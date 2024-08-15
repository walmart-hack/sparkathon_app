package com.example.walmart_sparkathon.Views

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.walmart_sparkathon.Models.FileNameHolder
import java.io.File

@Composable
fun UserFinalMap(navController: NavController){
    val context = LocalContext.current
    val imagePath = FileNameHolder.imagePath
    val imageFile = imagePath?.let { File(it) }
    val imageUri = imageFile?.let { Uri.fromFile(it) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        imageUri?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(300.dp)
            )
        } ?: run {
            Text("No image to display")
        }
    }
}