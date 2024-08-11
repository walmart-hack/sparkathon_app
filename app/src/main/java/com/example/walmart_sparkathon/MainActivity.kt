package com.example.walmart_sparkathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walmart_sparkathon.Views.AdminScreen
import com.example.walmart_sparkathon.Views.LoginScreen
import com.example.walmart_sparkathon.Views.UserScreen
import com.example.walmart_sparkathon.ui.theme.Walmart_sparkathonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
private fun MainScreen(){
    val navController = rememberNavController()
    Walmart_sparkathonTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            innerPadding->
            NavHost(navController = navController,
                startDestination = "login_screen",
                modifier = Modifier.padding(innerPadding)
            ){
                composable(route = "login_screen"){
                    LoginScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(route = "user_screen"){
                    UserScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(route = "admin_screen"){
                    AdminScreen(navController = navController, viewModel = hiltViewModel())
                }
            }
        }
    }
}