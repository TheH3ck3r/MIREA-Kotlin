package com.example.pr9

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentInfo(fullName: String, groupNumber: String) {
    val fullNameStyle = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily.Serif,
        color = Color.Black
    )

    val groupStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif,
        color = Color.Gray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = fullName, style = fullNameStyle)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = groupNumber, style = groupStyle)
    }
}