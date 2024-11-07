package com.example.pr9

import androidx.compose.foundation.isSystemInDarkTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.darkColors
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF1B5E20),
    primaryVariant = Color(0xFF003300),
    secondary = Color(0xFF4CAF50)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF4CAF50),
    primaryVariant = Color(0xFF388E3C),
    secondary = Color(0xFF81C784)
)

@Composable
fun StudentAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    // Заглушка, крайне кривая
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}