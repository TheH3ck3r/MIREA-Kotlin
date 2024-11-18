package com.example.pr78

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.File

class ImageRepositoryTest {

    private val imageRepository = ImageRepository()
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun downloadImage_validUrl_returnsBitmap() = runBlocking {
        val imageUrl = "https://example.com/image.png"
        val bitmap = imageRepository.downloadImage(imageUrl)
        assertNotNull(bitmap)
    }

    @Test
    fun downloadImage_invalidUrl_returnsNull() = runBlocking {
        val imageUrl = "https://invalid-url.com/image.png"
        val bitmap = imageRepository.downloadImage(imageUrl)
        assertNull(bitmap)
    }

    @Test
    fun saveImageToDisk_savesSuccessfully() = runBlocking {
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        imageRepository.saveImageToDisk(context, bitmap)
        val file = File(context.filesDir, "downloaded_image.png")
        assertTrue(file.exists())
    }

    @Test
    fun saveImageToDisk_fileIsSavedCorrectly() = runBlocking {
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        imageRepository.saveImageToDisk(context, bitmap)
        val file = File(context.filesDir, "downloaded_image.png")
        val savedBitmap = BitmapFactory.decodeFile(file.path)
        assertNotNull(savedBitmap)
    }

    @Test
    fun saveImageToDisk_overwritesExistingFile() = runBlocking {
        val bitmap1 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val bitmap2 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        imageRepository.saveImageToDisk(context, bitmap1)
        imageRepository.saveImageToDisk(context, bitmap2)
        val file = File(context.filesDir, "downloaded_image.png")
        val savedBitmap = BitmapFactory.decodeFile(file.path)
        assertEquals(200, savedBitmap.width)
        assertEquals(200, savedBitmap.height)
    }
}