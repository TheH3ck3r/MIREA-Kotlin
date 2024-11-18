package com.example.pr78

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val context = LocalContext.current
    val imageRepository = remember { ImageRepository(context) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Image Loader App") })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                BasicTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(50.dp),
                    decorationBox = { innerTextField ->
                        Box(Modifier.fillMaxSize()) {
                            if (imageUrl.text.isEmpty()) {
                                Text("Enter image URL", style = MaterialTheme.typography.bodySmall)
                            }
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (imageUrl.text.isNotEmpty()) {
                            coroutineScope.launch {
                                val loadedImage = imageRepository.downloadImage(imageUrl.text)
                                if (loadedImage != null) {
                                    bitmap = loadedImage
                                    val saved = imageRepository.saveImageToInternalStorage(loadedImage, "downloaded_image")
                                    withContext(Dispatchers.Main) {
                                        if (saved) {
                                            Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT).show()
                                        } else {
                                            Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                } else {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(context, "Error loading image", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Download and Save Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                }
            }
        }
    )
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
