package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.ViewModels.LoginScreenViewModel
import com.example.walmart_sparkathon.ViewModels.UserType
import com.example.walmart_sparkathon.ui.theme.ErrColor
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.OnSecondary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.SuccessColor

@Composable
fun LoginScreen(navController: NavController,viewModel: LoginScreenViewModel){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var store by remember { mutableStateOf("") }
    var loginState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Make shopping easy.", fontSize = 28.sp, color = PrimaryColor, fontWeight = FontWeight.SemiBold)
            }

            Column(modifier = Modifier
                .fillMaxWidth(0.8f)) {
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SecondaryColor, RoundedCornerShape(12.dp))
                        .size(60.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = OnPrimary,
                        focusedContainerColor = OnPrimary,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )



                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SecondaryColor, RoundedCornerShape(12.dp))
                        .size(60.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = OnPrimary,
                        focusedContainerColor = OnPrimary,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = store,
                    onValueChange = { store = it },
                    label = { Text("Store Name") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SecondaryColor, RoundedCornerShape(12.dp))
                        .size(60.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = OnPrimary,
                        focusedContainerColor = OnPrimary,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
        Button(
            onClick = {
                // Add your login logic here
                        val userType = viewModel.handleLogin(user_id = username,password = password)
                        if(userType == UserType.User){
                            navController.navigate("user_screen")
                        }else if(userType == UserType.Admin){
                            navController.navigate("admin_screen")
                        }
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonColors(
                containerColor = PrimaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("SIGN UP", modifier = Modifier.padding(15.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var store by remember { mutableStateOf("") }
    var loginState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Make shopping easy.", fontSize = 28.sp, color = PrimaryColor, fontWeight = FontWeight.SemiBold)
            }

            Column(modifier = Modifier
                .fillMaxWidth(0.8f)) {
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SecondaryColor, RoundedCornerShape(12.dp))
                        .size(60.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = OnPrimary,
                        focusedContainerColor = OnPrimary,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )



                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SecondaryColor, RoundedCornerShape(12.dp))
                        .size(60.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = OnPrimary,
                        focusedContainerColor = OnPrimary,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = store,
                    onValueChange = { store = it },
                    label = { Text("Store Name") },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, SecondaryColor, RoundedCornerShape(12.dp))
                        .size(60.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = OnPrimary,
                        focusedContainerColor = OnPrimary,
                        unfocusedTextColor = Color.Black,
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
        Button(
            onClick = {
                // Add your login logic here
//                        val userType = viewModel.handleLogin(user_id = username,password = password)
//                        if(userType == UserType.User){
//                            navController.navigate("user_screen")
//                        }else if(userType == UserType.Admin){
//                            navController.navigate("admin_screen")
//                        }
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonColors(
                containerColor = PrimaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text("SIGN UP", modifier = Modifier.padding(15.dp))
        }
    }
}