package com.example.walmart_sparkathon.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.ViewModels.LoginScreenViewModel
import com.example.walmart_sparkathon.ViewModels.UserType
import com.example.walmart_sparkathon.ui.theme.ErrColor
import com.example.walmart_sparkathon.ui.theme.SuccessColor

@Composable
fun LoginScreen(navController: NavController,viewModel: LoginScreenViewModel){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loginState) {
            Text("Login Successful!", color = SuccessColor)
        } else if (username.isNotEmpty() && password.isNotEmpty()) {
            Text("Invalid credentials, please try again.", color = ErrColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Add your login logic here
                loginState = username == "user" && password == "password" // Mock login logic
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loginState) {
            Text("Login Successful!", color = SuccessColor)
        } else if (username.isNotEmpty() && password.isNotEmpty()) {
            Text("Invalid credentials, please try again.", color = ErrColor)
        }
    }
}