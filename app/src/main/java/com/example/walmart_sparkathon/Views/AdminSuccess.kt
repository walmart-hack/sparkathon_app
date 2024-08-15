package com.example.walmart_sparkathon.Views

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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.composables.TopBar
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.TertiaryColor

@Composable
fun AdminSuccess(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
                        painter = painterResource(R.drawable.bag),
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
                    Text(text = "You’re all set!", color = OnPrimary, fontSize = 20.sp)
                    Text(text = "Hurray!",color = OnPrimary, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Column (modifier = Modifier.fillMaxWidth(0.8f)){
            Text(fontSize = 30.sp,
                lineHeight = 40.sp,
                text = "Your customers can now enjoy a hassle-free shopping experience!",
                color = SecondaryColor
            )
        }

        Spacer(modifier = Modifier.height(150.dp))

        Row(modifier = Modifier.fillMaxWidth(0.9f),) {
            Button(onClick = {navController.navigate("enter_screen") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = TertiaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(20.dp)) {
                Text("Customise your store", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
                Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
                Icon(
                    imageVector = Icons.Default.Settings, // Use the built-in arrow forward icon
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}