package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.TertiaryColor

@Composable
fun CustomerOnBoard(navController: NavController){
    val stores = listOf(
        "Bangalore - Whitefield",
        "Thane - Mumbai",
    )
    var endDestination by remember { mutableStateOf<String?>(null) }
    var isStartDropdownExpanded by remember { mutableStateOf(false) }
    var isEndDropdownExpanded by remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column (modifier = Modifier.fillMaxWidth(0.8f)){
            Text(fontSize = 30.sp,
                lineHeight = 40.sp,
                text = "Your shopping ninja awaits.",
                color = PrimaryColor
            )
        }


        Column (modifier = Modifier.fillMaxWidth(0.8f)){
            Text(fontSize = 30.sp,
                lineHeight = 40.sp,
                text = "Choose the store you are present in:",
                color = SecondaryColor
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(modifier = Modifier.fillMaxWidth(0.8f)) {
            OutlinedTextField(
                value = endDestination ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isEndDropdownExpanded = !isEndDropdownExpanded },
                readOnly = true,
                label = { Text("Store Name") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-down arrow",
                        Modifier.clickable { isEndDropdownExpanded = !isEndDropdownExpanded }
                    )
                }
            )
            DropdownMenu(
                expanded = isEndDropdownExpanded,
                onDismissRequest = { isEndDropdownExpanded = false },
                modifier = Modifier.fillMaxWidth(0.8f).background(OnPrimary)
            ) {
                stores.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = category) },
                        onClick = {
                            endDestination = category
                            isEndDropdownExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.fillMaxWidth(0.8f),) {
            Button(onClick = { navController.navigate("user_screen") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = TertiaryColor,
                    contentColor = OnPrimary,
                    disabledContentColor = OnPrimary,
                    disabledContainerColor = PrimaryColor
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Continue", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
                Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
                Icon(
                    imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}