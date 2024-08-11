package com.example.walmart_sparkathon.Views

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.ViewModels.UserScreenViewModel
import com.example.walmart_sparkathon.ui.theme.SuccessColor
import kotlinx.coroutines.delay

@Composable
fun UserScreen(navController: NavController, viewModel: UserScreenViewModel){
    var currentItem by remember { mutableStateOf("") }
    var itemsList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Enter items")

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = currentItem,
            onValueChange = { currentItem = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(SuccessColor, MaterialTheme.shapes.small)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (currentItem.isNotBlank()) {
                    itemsList = itemsList + currentItem
                    currentItem = "" // Clear input field
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (itemsList.isNotEmpty()) {
            Text(
                text = "Items:",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                itemsList.forEachIndexed { index, item ->
                    ItemView(
                        item = item,
                        onItemClicked = {
                            itemsList = itemsList.toMutableList().apply { removeAt(index) }
                        },
                        onItemLongClicked = {
                            itemsList = itemsList.toMutableList().apply { removeAt(index) }
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserScreenPreview(){
    var currentItem by remember { mutableStateOf("") }
    var itemsList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Enter items")

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = currentItem,
            onValueChange = { currentItem = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(SuccessColor, MaterialTheme.shapes.small)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (currentItem.isNotBlank()) {
                    itemsList = itemsList + currentItem
                    currentItem = "" // Clear input field
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (itemsList.isNotEmpty()) {
            Text(
                text = "Items:",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                itemsList.forEachIndexed { index, item ->
                    ItemView(
                        item = item,
                        onItemClicked = {
                            itemsList = itemsList.toMutableList().apply { removeAt(index) }
                        },
                        onItemLongClicked = {
                            itemsList = itemsList.toMutableList().apply { removeAt(index) }
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun ItemView(item: String, onItemClicked: () -> Unit, onItemLongClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(SuccessColor)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onItemClicked()
                    },
                    onLongPress = {
                        onItemLongClicked()
                    }
                )
            }
            .padding(16.dp)
    ) {
        Text(text = item)
    }
}
