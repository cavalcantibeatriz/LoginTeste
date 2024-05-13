package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilefaztudo.R


@Composable
fun BackgroundRegister(backgroundImageResId : Int) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = backgroundImageResId),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center
        )
    }


}

val imagem: Int = R.drawable.background
@Preview(showBackground = true)
@Composable
fun BackgroundRegisterPreview() {
    BackgroundRegister(backgroundImageResId = imagem)
}