package com.example.walmart_sparkathon.composables

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.ui.theme.GrayColor
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor

@Composable
@Preview(showBackground = true)
fun TopBarUser(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryColor)
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.padding(top = 30.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(text = "Shop Fast.", color = OnPrimary, fontSize = 24.sp)
                Text(text = "Shop Easy", color = OnPrimary, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "STORE",color = GrayColor, fontSize = 12.sp)

                Text(text = "Bangalore - Whitefield",color = OnPrimary, fontSize = 16.sp)
            }
            Column(verticalArrangement = Arrangement.Bottom) {
                Image(
                    painter = painterResource(R.drawable.store_ninja),
                    contentDescription = "Example Image", // Description for accessibility
                    modifier = Modifier.fillMaxWidth() // Adjust the size as needed
                )
            }
        }
    }
}