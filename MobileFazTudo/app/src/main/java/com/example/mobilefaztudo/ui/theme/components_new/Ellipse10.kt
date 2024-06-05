package com.example.mobilefaztudo.ui.theme.components_new

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilefaztudo.R

@Composable
fun Ellipse10(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.background(Color.Transparent)) {
        Box(
            modifier = modifier
                .requiredSize(size = 198.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xffd9d9d9)),
            contentAlignment = Alignment.Center  // Centraliza o conteúdo da Box
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.rectangle_71__1_),
                contentDescription = "Botão de Voltar",
                contentScale = ContentScale.Crop  // Ajusta a imagem para preencher a Box
            )
        }
        Image(
            modifier = Modifier
                .size(50.dp) // Define o tamanho da imagem menor
                .align(Alignment.TopEnd) // Alinha a imagem menor no canto superior direito
                .offset(
                    x = (-8).dp,
                    y = 8.dp
                ), // Ajusta a posição com um pequeno deslocamento se necessário
            painter = painterResource(id = R.drawable.group),
            contentDescription = "Imagem Menor"
        )
    }
}


@Preview
@Composable
private fun Ellipse10Preview() {
    Ellipse10(Modifier)
}