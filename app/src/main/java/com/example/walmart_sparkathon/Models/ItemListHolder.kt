package com.example.walmart_sparkathon.Models

import javax.inject.Singleton

@Singleton
object ItemListHolder {
    var item_list : List<String>? = null
    var user_list : List<String>? = null
}