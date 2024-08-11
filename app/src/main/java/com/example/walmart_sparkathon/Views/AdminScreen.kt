package com.example.walmart_sparkathon.Views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.walmart_sparkathon.ViewModels.AdminScreenViewModel

@Composable
fun AdminScreen(navController: NavController,viewModel: AdminScreenViewModel){
    Text(text = "Admin Screen")
}

@Composable
@Preview(showBackground = true)
fun AdminScreenPreview(){
    Text("Admin screen")
}