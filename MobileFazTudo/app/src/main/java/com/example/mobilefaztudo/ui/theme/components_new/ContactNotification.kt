package com.example.mobilefaztudo.ui.theme.components_new

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip

@Composable
fun Frame7(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .requiredWidth(292.dp)
            .requiredHeight(156.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 20.dp, y = 30.dp)
                .requiredWidth(252.dp)
                .requiredHeight(96.dp)
        ) {
            Column(
                modifier = Modifier
                    .requiredWidth(252.dp)
                    .requiredHeight(96.dp)
            ) {
                Text(
                    text = "Enviar contato para Júlio :",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(y = 0.dp)
                )
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = {
                        Text(
                            text = "Digite aqui",
                            color = Color.Gray,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(y = 10.dp)
                        .requiredWidth(212.dp)
                        .requiredHeight(28.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xffff5c00).copy(alpha = 0.2f)),
                    textStyle = TextStyle(fontSize = 16.sp),
                    singleLine = true
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 20.dp, y = 109.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(99.dp)
                    .requiredHeight(36.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color(0xff992323))
                    .clickable { /* Ação de cancelar */ }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cancelar",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 173.dp, y = 109.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(99.dp)
                    .requiredHeight(36.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color(0xff239960))
                    .clickable { /* Ação de continuar */ }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Continuar",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}


@Preview(widthDp = 292, heightDp = 156)
@Composable
private fun Frame7Preview() {
    Frame7(Modifier)
}