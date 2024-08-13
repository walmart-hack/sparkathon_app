package com.example.walmart_sparkathon.ViewModels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AdminScreenViewModel @Inject constructor() : ViewModel() {
    // MutableStateFlow to store and update the selected image URI
    private val _selectedImageUri = MutableStateFlow<Uri?>(null)

    // Expose the state as immutable StateFlow
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    // Function to update the selected image URI
    fun updateImageUri(uri: Uri?) {
        _selectedImageUri.value = uri
    }

    fun send_walmart_image(selectedImageUri : Uri?) {

        // Create the request body
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val client = OkHttpClient()
                val mediaType = "text/plain".toMediaType()
                val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("image","anyName",
                        File("/Users/priyanshujain13/Downloads/WhatsApp Image 2024-08-10 at 17.50.29.jpeg").asRequestBody("application/octet-stream".toMediaType()))
                    .build()
                val request = Request.Builder()
                    .url("http://192.168.29.69:8000/convert-to-grid")
                    .post(body)
                    .build()
                val response = client.newCall(request).execute()
                Log.d(response.toString(),"response")
            } catch (e: Exception) {
                // Handle the exception, possibly show a message to the user
                e.printStackTrace()
            }
        }
    }
}