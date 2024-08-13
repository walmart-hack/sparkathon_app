package com.example.walmart_sparkathon.ViewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {

    fun handleLogin(user_id : String, password : String) : UserType{
        if(user_id == "admin" && password == "12345"){
            return UserType.Admin
        }else if(user_id == "user" && password == "12345"){
            return UserType.User
        }else{
            return UserType.Invalid
        }
    }
}

enum class UserType {
    Admin,//store manager
    User,//customers
    Invalid
}