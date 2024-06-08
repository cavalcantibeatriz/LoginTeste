package com.example.mobilefaztudo.ui.theme.components_new


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun FotoDemandaCompleta(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.background(Color.Transparent)) {
        Box(
            modifier = modifier
                .requiredSize(size = 198.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center  // Centraliza o conteúdo da Box
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.rectangle_71__1_),
                contentDescription = "Botão de Voltar",
                contentScale = ContentScale.Crop  // Ajusta a imagem para preencher a Box
            )
        }
    }
}


@Preview
@Composable
private fun FotoDemandaCompletaPreview() {
    FotoDemandaCompleta(Modifier)
}