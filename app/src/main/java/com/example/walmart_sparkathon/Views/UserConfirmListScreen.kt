package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.walmart_sparkathon.Models.ItemListHolder

@Composable
fun UserConfirmScreen(navController: NavController){
    val user_list = ItemListHolder.user_list
    val item_list = ItemListHolder.item_list

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .background(Color.White)) {
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {

                user_list?.map { user->
                    Text(text = user, color = Color.Black
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                item_list?.map { user->
                    Text(text = user,color = Color.Black)
                }
            }
        }

        Button(onClick = { navController.navigate("user_start_end_screen") }) {
            Text(text = "Confirm")
        }
    }
}