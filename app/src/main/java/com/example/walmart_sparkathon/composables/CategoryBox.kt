package com.example.walmart_sparkathon.composables

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.walmart_sparkathon.ui.theme.LightGrayColor
import com.example.walmart_sparkathon.ui.theme.OnSecondary
import java.util.Locale.Category

@Composable
fun CategoryBox(user: String, item: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(8.dp)) // Add shadow with rounded corners
            .background(LightGrayColor)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = user, color = OnSecondary, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = item, color = OnSecondary, fontWeight = FontWeight.Light)
    }
    Spacer(modifier = Modifier.height(10.dp))
}