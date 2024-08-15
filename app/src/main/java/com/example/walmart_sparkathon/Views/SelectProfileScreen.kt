package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SelectProfileScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()) {
        Text(text = "Select Your Profile")

        Button(
            onClick = {
                navController.navigate("user_screen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Customer")
        }

        Button(
            onClick = {
                navController.navigate("login_screen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Store Manager")
        }
    }
}