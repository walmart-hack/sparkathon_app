package com.example.walmart_sparkathon

import UserPathSelectScreen
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.walmart_sparkathon.Views.AdminScreen
import com.example.walmart_sparkathon.Views.AdminSuccess
import com.example.walmart_sparkathon.Views.ImageTapScreen
import com.example.walmart_sparkathon.Views.LoginScreen
import com.example.walmart_sparkathon.Views.SelectProfileScreen
import com.example.walmart_sparkathon.Views.UserConfirmScreen
import com.example.walmart_sparkathon.Views.UserFinalMap
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
                startDestination = "enter_screen",
                modifier = Modifier.padding(innerPadding)
            ){
                composable(route = "enter_screen"){
                    SelectProfileScreen(navController = navController)
                }
                composable(route = "login_screen"){
                    LoginScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(route = "user_screen"){
                    UserScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(route = "admin_screen"){
                    AdminScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(route = "grid_screen"){
                    ImageTapScreen(navController = navController, viewModel = hiltViewModel())
                }
                composable(route = "admin_success"){
                    AdminSuccess()
                }
                composable(route = "user_confirm_screen"){
                    UserConfirmScreen(navController = navController)
                }
                composable(route = "user_start_end_screen"){
                    UserPathSelectScreen(navController = navController)
                }
                composable(
                    route = "user_map"
                ) {
                    UserFinalMap(navController = navController)
                }
            }
        }
    }
}
