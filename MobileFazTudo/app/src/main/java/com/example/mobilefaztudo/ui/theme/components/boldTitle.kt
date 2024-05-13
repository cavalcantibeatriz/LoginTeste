package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BoldTilte(text: String){
    Text(
        text = text,
        color = Color.White,
        style = MaterialTheme.typography.titleLarge
    )
}