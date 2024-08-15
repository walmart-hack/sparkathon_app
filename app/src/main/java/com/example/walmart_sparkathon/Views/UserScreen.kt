package com.example.walmart_sparkathon.Views

import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.Models.ItemListHolder
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.ViewModels.UserScreenViewModel
import com.example.walmart_sparkathon.composables.TopBar
import com.example.walmart_sparkathon.composables.TopBarUser
import com.example.walmart_sparkathon.ui.theme.LightYellow
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.SuccessColor
import com.example.walmart_sparkathon.ui.theme.TertiaryColor
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException

@Composable
fun UserScreen(navController: NavController, viewModel: UserScreenViewModel){
    var currentItem by remember { mutableStateOf("") }
    var itemsList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarUser()

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(SecondaryColor)
                .padding(15.dp)
        ) {
            Row {
                Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Image(
                        painter = painterResource(R.drawable.list),
                        contentDescription = "Example Image", // Description for accessibility
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(text = "Enter your shopping list.", color = OnPrimary, fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = currentItem,
            onValueChange = { currentItem = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(1.dp, PrimaryColor, shape = RoundedCornerShape(10.dp)) // Rounded border
                .clip(RoundedCornerShape(10.dp)) // Apply the same shape to clip the content inside
                .background(OnPrimary, shape = RoundedCornerShape(10.dp)) // Rounded background
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (currentItem.isNotBlank()) {
                    itemsList = itemsList + currentItem
                    currentItem = "" // Clear input field
                }
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonColors(
                containerColor = TertiaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            )

        ) {
            Text("Add Item + ", fontSize = 18.sp, modifier = Modifier.padding(15.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
                .verticalScroll(rememberScrollState()) // Enables vertical scrolling
        ) {
            if (itemsList.isNotEmpty()) {
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


        Button(
            onClick = {
                fetch_customer_item_list(itemsList)
                navController.navigate("user_confirm_screen")
            },
            colors = ButtonColors(
                containerColor = TertiaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Continue", fontSize = 18.sp, modifier = Modifier.padding(15.dp))
            Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
            Icon(
                imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                contentDescription = "Next",
                modifier = Modifier.size(24.dp)
            )
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
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarUser()

        Spacer(modifier = Modifier.height(20.dp))
        
        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(SecondaryColor)
                .padding(15.dp)
        ) {
            Row {
                Column(modifier = Modifier.fillMaxWidth(0.4f)) {
                    Image(
                        painter = painterResource(R.drawable.list),
                        contentDescription = "Example Image", // Description for accessibility
                        modifier = Modifier.size(60.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start) {
                    Text(text = "Enter your shopping list.", color = OnPrimary, fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = currentItem,
            onValueChange = { currentItem = it },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(1.dp, PrimaryColor, shape = RoundedCornerShape(10.dp)) // Rounded border
                .clip(RoundedCornerShape(10.dp)) // Apply the same shape to clip the content inside
                .background(OnPrimary, shape = RoundedCornerShape(10.dp)) // Rounded background
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
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonColors(
                containerColor = TertiaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            )

        ) {
            Text("Add Item + ", fontSize = 18.sp, modifier = Modifier.padding(15.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column (modifier = Modifier.fillMaxWidth(0.9f).height(250.dp)){
            if (itemsList.isNotEmpty()) {
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

        Button(
            onClick = {
//                fetch_customer_item_list(itemsList)
//                navController.navigate("user_confirm_screen")
            },
            colors = ButtonColors(
                containerColor = TertiaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Continue", fontSize = 18.sp, modifier = Modifier.padding(15.dp))
            Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
            Icon(
                imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                contentDescription = "Next",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ItemView(item: String, onItemClicked: () -> Unit, onItemLongClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(LightYellow)
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

fun fetch_customer_item_list(itemsList: List<String>) {
    // Create the JSON object to send
    val requestBodyJson = JsonObject().apply {
        add("customer_item_list", Gson().toJsonTree(itemsList))
    }.toString()

    // Create the request body
    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val requestBody = requestBodyJson.toRequestBody(mediaType)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://192.168.1.6:8000/customer-item-list")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build()

            // Make the request
            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            }

            // Parse the response
            val responseBody = response.body?.string()
            val jsonResponse = Gson().fromJson(responseBody, JsonObject::class.java)
            val status = jsonResponse.get("status").asString

            if (status == "success") {
                val output = jsonResponse.getAsJsonArray("output")
                val outputList = output.map { it.asString }

                ItemListHolder.item_list = outputList
                ItemListHolder.user_list = itemsList
                // Process the output list as needed
                println("Received output: $outputList")
                println("Received output: ${ItemListHolder.item_list}")
                // Example: Update UI here if necessary

            } else {
                // Handle API failure status
                println("API returned failure status")
            }

        } catch (e: Exception) {
            // Handle the exception, possibly show a message to the user
            println("Error: ${e.message}")
            e.printStackTrace()
        }
    }
}