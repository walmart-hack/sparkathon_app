package com.example.walmart_sparkathon.ViewModels

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.walmart_sparkathon.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
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

    private val _fileName = MutableStateFlow<String?>(null)
    val fileName: StateFlow<String?> = _fileName

    private val _width = MutableStateFlow<Int?>(null)
    val width: StateFlow<Int?> = _width

    private val _height = MutableStateFlow<Int?>(null)
    val height: StateFlow<Int?> = _height

    fun send_walmart_image(context: Context, selectedImageUri: Uri?) {
        if (selectedImageUri == null) {
            Log.e("AdminScreenViewModel", "No image selected")
            return
        }

        val imageFile = uriToFile(context, selectedImageUri)

        if (imageFile != null) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val client = OkHttpClient()
                    val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("image", imageFile.name, imageFile.asRequestBody("application/octet-stream".toMediaType()))
                        .build()
                    val request = Request.Builder()
                        .url("http:///192.168.1.6:8000/convert-to-grid")
                        .post(body)
                        .build()
                    val response = client.newCall(request).execute()

                    // Check if the response is successful
                    if (response.isSuccessful) {
                        response.body?.let { responseBody ->
                            // Parse JSON response
                            val responseString = responseBody.string()
                            val jsonObject = JSONObject(responseString)
                            val fileName = jsonObject.optString("file_name")
                            val shapeArray = jsonObject.optJSONArray("shape")
                            val width = shapeArray?.optInt(0)
                            val height = shapeArray?.optInt(1)

                            // Update MutableStateFlow with extracted values
                            _fileName.value = fileName
                            _width.value = width
                            _height.value = height

                            // Log or use extracted values
                            Log.d("AdminScreenViewModel", "File Name: $fileName")
                            Log.d("AdminScreenViewModel", "Width: $width, Height: $height")
                        }
                    } else {
                        Log.e("AdminScreenViewModel", "Response not successful: ${response.message}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            Log.e("AdminScreenViewModel", "Failed to resolve URI to file")
        }
    }


    fun uriToFile(context: Context, uri: Uri): File? {
        val contentResolver = context.contentResolver
        val fileName = contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(nameIndex)
        }

        val inputStream = contentResolver.openInputStream(uri) ?: return null
        val tempFile = File(context.cacheDir, fileName ?: "temp_file")

        inputStream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return tempFile
    }


}