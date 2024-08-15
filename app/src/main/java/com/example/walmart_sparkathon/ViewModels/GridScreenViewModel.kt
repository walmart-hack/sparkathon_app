package com.example.walmart_sparkathon.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.walmart_sparkathon.Models.FileNameHolder
import com.example.walmart_sparkathon.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

data class Point(val name : String , val coordinates : List<Int>)

class GridScreenViewModel : ViewModel() {

    private val points = mutableListOf<Point>()
    val fileName = FileNameHolder.fileName

    fun addCategory(xCoordinate: Int, yCoordinate: Int, categoryName: String) {
        points.add(Point(categoryName, listOf(xCoordinate, yCoordinate)))
        Log.d("GridScreenViewModel", "$points")
    }

    fun submitCategory() {
        Log.d("GridScreenViewModel", "Submitting categories")

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Configure OkHttpClient with custom timeout settings
                val client = OkHttpClient.Builder()
                    .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS) // Connection timeout
                    .writeTimeout(20, java.util.concurrent.TimeUnit.SECONDS)   // Write timeout
                    .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)    // Read timeout
                    .build()

                // Define the JSON body
                val jsonBody = JSONObject().apply {
                    put("image_name", fileName)
                    put("points", JSONArray().apply {
                        points.forEach { point ->
                            put(JSONObject().apply {
                                put("name", point.name)
                                put("coordinates", JSONArray(point.coordinates))
                            })
                        }
                    })
                }.toString()

                // Define the media type and request body
                val mediaType = "application/json".toMediaType()
                val requestBody = jsonBody.toRequestBody(mediaType)
                Log.d("GridScreenViewModel", "$jsonBody")

                // Define the request
                val request = Request.Builder()
                    .url("http://192.168.1.6:8000/insert-coordinates")
                    .post(requestBody)
                    .build()

                // Make the API call
                val response = client.newCall(request).execute()

                // Check if the response is successful
                if (response.isSuccessful) {
                    response.body?.let { responseBody ->
                        val responseString = responseBody.string()
                        // Handle response JSON if needed
                        Log.d("GridScreenViewModel", "API call successful: $responseString")
                    }
                } else {
                    Log.e("GridScreenViewModel", "Response not successful: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("GridScreenViewModel", "API call error: ${e.message}", e)
            }
        }
    }
}

//val client = OkHttpClient()
//val mediaType = "text/plain".toMediaType()
//val body = "{\n    \"image_name\": \"f3a153df-e122-4518-a171-e25e0b49fdd7_all_cat.jpg\",\n    \"points\": [\n        {\"name\": \"category1\", \"coordinates\": [108, 100]},\n        {\"name\": \"category2\", \"coordinates\": [300, 800]}\n    ]\n}".toRequestBody(mediaType)
//val request = Request.Builder()
//    .url("http://localhost:5000/insert-coordinates")
//    .post(body)
//    .build()
//val response = client.newCall(request).execute()
//
//{
//    "image_name": "f3a153df-e122-4518-a171-e25e0b49fdd7_all_cat.jpg",
//    "points": [
//    {"name": "category1", "coordinates": [108, 100]},
//    {"name": "category2", "coordinates": [300, 800]}
//    ]
//}

//*******************

//val client = OkHttpClient()
//val request = Request.Builder()
//    .url("http://localhost:8000/list-categories")
//    .build()
//val response = client.newCall(request).execute()