import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.walmart_sparkathon.Models.FileNameHolder
import com.example.walmart_sparkathon.Models.ItemListHolder
import com.example.walmart_sparkathon.R
import com.example.walmart_sparkathon.composables.TopBarUser
import com.example.walmart_sparkathon.ui.theme.OnPrimary
import com.example.walmart_sparkathon.ui.theme.PrimaryColor
import com.example.walmart_sparkathon.ui.theme.SecondaryColor
import com.example.walmart_sparkathon.ui.theme.TertiaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

@Composable
fun UserPathSelectScreen(navController: NavController) {
    val categories = listOf(
        "Animals & Pet Supplies",
        "Apparel & Accessories",
        "Arts & Entertainment",
        "Baby & Toddler",
        "Business & Industrial",
        "Cameras & Optics",
        "Electronics",
        "Food, Beverages & Tobacco",
        "Furniture",
        "Hardware",
        "Health & Beauty",
        "Home & Garden",
        "Luggage & Bags",
        "Media",
        "Office Supplies",
        "Religious & Ceremonial",
        "Software",
        "Sporting Goods",
        "Toys & Games",
        "Vehicles & Parts"
    )

    var mappedUserCategories = ItemListHolder.item_list

    var startDestination by remember { mutableStateOf<String?>(null) }
    var endDestination by remember { mutableStateOf<String?>("anyValue") }
    var isStartDropdownExpanded by remember { mutableStateOf(false) }
    var isEndDropdownExpanded by remember { mutableStateOf(false) }
    var imagePath by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(OnPrimary),
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
                    Text(text = "Choose your start point", color = OnPrimary, fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Box {
            OutlinedTextField(
                value = startDestination ?: "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable { isStartDropdownExpanded = !isStartDropdownExpanded },
                readOnly = true,
                label = { Text("Start Destination") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-down arrow",
                        Modifier.clickable { isStartDropdownExpanded = !isStartDropdownExpanded }
                    )
                }
            )
            DropdownMenu(
                expanded = isStartDropdownExpanded,
                onDismissRequest = { isStartDropdownExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = category) },
                        onClick = {
                            startDestination = category
                            isStartDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(180.dp))

//        Text("Select End Destination")

//        Box {
//            OutlinedTextField(
//                value = endDestination ?: "",
//                onValueChange = {},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable { isEndDropdownExpanded = !isEndDropdownExpanded },
//                readOnly = true,
//                label = { Text("End Destination") },
//                trailingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.ArrowDropDown,
//                        contentDescription = "Drop-down arrow",
//                        Modifier.clickable { isEndDropdownExpanded = !isEndDropdownExpanded }
//                    )
//                }
//            )
//            DropdownMenu(
//                expanded = isEndDropdownExpanded,
//                onDismissRequest = { isEndDropdownExpanded = false },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                categories.forEach { category ->
//                    DropdownMenuItem(
//                        text = { Text(text = category) },
//                        onClick = {
//                            endDestination = category
//                            isEndDropdownExpanded = false
//                        }
//                    )
//                }
//            }
//        }

        Button(
            onClick = {
                if (startDestination != null && endDestination != null) {
                    FileNameHolder.imageName?.let {
                        if (mappedUserCategories != null) {
                            getImage(
                                context = context,
                                imageName = it,
                                categories = mappedUserCategories,
                                startLocation = startDestination!!,
                                endLocation = endDestination!!,
                                onImagePathReady = { path ->
                                    imagePath = path
                                    // Ensure imagePath is available before navigating
                                    Log.d("UserPathScreen","$imagePath")
                                    FileNameHolder.imagePath = imagePath
                                    if (imagePath != null) {
                                        navController.navigate("user_map")
                                    }
                                }
                            )
                        }
                    }
                }
            },
            colors = ButtonColors(
                containerColor = TertiaryColor,
                contentColor = OnPrimary,
                disabledContentColor = OnPrimary,
                disabledContainerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(0.9f),
            enabled = startDestination != null && endDestination != null,

        ) {
            Text("Explore Your Path", fontSize = 18.sp, modifier = Modifier.padding(15.dp))
            Spacer(modifier = Modifier.width(8.dp)) // Add some spacing between the text and the icon
            Icon(
                imageVector = Icons.Default.ArrowForward, // Use the built-in arrow forward icon
                contentDescription = "Next",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


fun getImage(
    context: Context,
    imageName: String,
    categories: List<String>,
    startLocation: String,
    endLocation: String,
    onImagePathReady: (String?) -> Unit // Callback to pass the image path
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val client = OkHttpClient()

            // Build the JSON body
            val json = JSONObject().apply {
                put("image_name", imageName)
                put("categories", categories)
                put("start_location", startLocation)
                put("end_location", endLocation)
            }.toString()

            val body = json.toRequestBody("application/json; charset=utf-8".toMediaType())

            val request = Request.Builder()
                .url("http://192.168.29.203:8000/generate-path") // Update with your API endpoint
                .post(body)
                .build()

            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val bitmap = BitmapFactory.decodeStream(responseBody.byteStream())
                    if (bitmap != null) {
                        val savedImagePath = saveImageToInternalStorage(context, bitmap, imageName)
                        Log.d("UserPathSelectScreen", "Image saved successfully")
                        withContext(Dispatchers.Main) {
                            onImagePathReady(savedImagePath) // Update the image path
                        }
                    } else {
                        Log.e("UserPathSelectScreen", "Failed to decode the image")
                    }
                }
            } else {
                Log.e("UserPathSelectScreen", "Response not successful: ${response.message}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("UserPathSelectScreen", "Exception: ${e.message}")
        }
    }
}

fun saveImageToInternalStorage(context: Context, bitmap: Bitmap, imageName: String): String? {
    val directory = context.filesDir
    val file = File(directory, imageName)
    val outputStream = FileOutputStream(file)

    return try {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("UserPathSelectScreen", "Failed to save image: ${e.message}")
        null
    } finally {
        outputStream.close()
    }
}
