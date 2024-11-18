package com.example.pr78

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val imageRepository = remember { ImageRepository(context) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text("Enter image URL") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (imageUrl.text.isNotEmpty()) {
                    coroutineScope.launch {
                        val loadedImage = withContext(Dispatchers.IO) {
                            imageRepository.downloadImage(imageUrl.text)
                        }
                        if (loadedImage != null) {
                            bitmap = loadedImage
                            val saved = withContext(Dispatchers.IO) {
                                imageRepository.saveImageToInternalStorage(loadedImage, "downloaded_image")
                            }
                            withContext(Dispatchers.Main) {
                                if (saved) {
                                    Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Error loading image", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Download and Save Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("details")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to Details Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                startWorkManagerTask()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start WorkManager Task")
        }
    }
}