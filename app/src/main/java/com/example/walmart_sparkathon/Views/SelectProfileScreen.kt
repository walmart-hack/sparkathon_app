package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.ui.theme.GrayColor
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.OnSecondary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor

@Composable
fun SelectProfileScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.store_ninja),
                contentDescription = "Example Image", // Description for accessibility
                modifier = Modifier.size(300.dp) // Adjust the size as needed
            )
        }

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(color = PrimaryColor,fontWeight = FontWeight.Bold, fontSize = 30.sp,text = "Shop Smart.")
            Text(color = PrimaryColor,fontWeight = FontWeight.Bold, fontSize = 30.sp,text = "Shop Fast.")
            Text(color = PrimaryColor,fontWeight = FontWeight.Bold, fontSize = 30.sp,text = "Shop Easy.")
        }
        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(color = GrayColor, fontSize = 18.sp,text = "Transform your shopping experience by virtually mapping your fastest route through the store.")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier
            .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {navController.navigate("user_screen")},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = PrimaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Customer", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
                Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {navController.navigate("login_screen")},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = SecondaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Store Manager", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
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

@Composable
@Preview
fun SelectProfileScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.store_ninja),
                contentDescription = "Example Image", // Description for accessibility
                modifier = Modifier.size(300.dp) // Adjust the size as needed
            )
        }

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(color = PrimaryColor,fontWeight = FontWeight.Bold, fontSize = 30.sp,text = "Shop Smart.")
            Text(color = PrimaryColor,fontWeight = FontWeight.Bold, fontSize = 30.sp,text = "Shop Fast.")
            Text(color = PrimaryColor,fontWeight = FontWeight.Bold, fontSize = 30.sp,text = "Shop Easy.")
        }
        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            Text(color = GrayColor, fontSize = 18.sp,text = "Transform your shopping experience by virtually mapping your fastest route through the store.")
        }

        Spacer(modifier = Modifier.height(60.dp))

        Column(modifier = Modifier
            .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = PrimaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Customer", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
                Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = SecondaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Store Manager", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
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