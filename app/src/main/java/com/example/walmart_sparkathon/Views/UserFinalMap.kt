package com.example.walmart_sparkathon.Views

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.walmart_sparkathon.Models.FileNameHolder
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.composables.TopBarUser
import com.example.walmart_sparkathon.ui.theme.GrayColor
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.TertiaryColor
import java.io.File

@Composable
fun UserFinalMap(navController: NavController){
    val context = LocalContext.current
    val imagePath = FileNameHolder.imagePath
    val imageFile = imagePath?.let { File(it) }
    val imageUri = imageFile?.let { Uri.fromFile(it) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarUser()
        
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(SecondaryColor)
                .padding(15.dp)
        ) {
            Row {
                Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Image(
                        painter = painterResource(R.drawable.maps_icon),
                        contentDescription = "Example Image", // Description for accessibility
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(text = "Your path mapped", color = OnPrimary, fontWeight = FontWeight.Light, fontSize = 20.sp)
                    Text(text = "within seconds", color = OnPrimary, fontSize = 24.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        imageUri?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        } ?: run {
            Text("No image to display")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {navController.navigate("enter_screen")},
            colors = ButtonColors(
                containerColor = TertiaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            ) {
            Text("Done shopping? Exit", fontSize = 18.sp, modifier = Modifier.padding(15.dp))
            Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
            Icon(
                imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                contentDescription = "Next",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}