package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilefaztudo.R
@Preview
@Composable
fun BackgroundPrestador() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(color = 0xFFF0C6AF))
    ){
        Image(
            painter = painterResource(id = R.drawable.vector2),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart
        )
    }
}

@Preview
@Composable
fun BackgroundNotificacao() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(color = 0xFFF0C6AF))
    ){
        Image(
            painter = painterResource(id = R.drawable.vector2),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart
        )
        Image(
            painter = painterResource(id = R.drawable.vector3),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart
        )
    }
}

@Preview
@Composable
fun BackgroundDemanda() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(color = 0xFFAFD6F0))){
        Image(
            painter = painterResource(id = R.drawable.vector3),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart
        )
    }
}

@Preview
@Composable
fun BackgroundPerfilPrestador() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Image(
            painter = painterResource(id = R.drawable.vector5),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart
        )
    }
}

@Preview
@Composable
fun BackgroundPerfilContratante() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Image(
            painter = painterResource(id = R.drawable.vector4),
            contentDescription = "Background image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart
        )
    }
}


