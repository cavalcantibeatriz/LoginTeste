package com.example.faztudo_mb.ui.theme.screens.components_new
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.Demanda

@Composable
fun DemandFinished(demanda: Demanda) {
    val lightGray = Color(0xFFD3D3D3)
    var showDemandaCompleta by remember { mutableStateOf(false) }
    fun categoria(id: Int): String {
        var categoriaSelecionada = ""
        if (id == 1) {
            categoriaSelecionada = "Mecânica"
        }
        if (id == 2) {
            categoriaSelecionada = "Hidraulica"
        }
        if (id == 3) {
            categoriaSelecionada = "Limpeza"
        }
        if (id == 4) {
            categoriaSelecionada = "Elétrica"
        }
        if (id == 5) {
            categoriaSelecionada = "Obras"
        }
        if (id == 6) {
            categoriaSelecionada = "Todos"
        }
        return categoriaSelecionada
    }


    Column(
        modifier = Modifier
            .width(350.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            Text(
                text = demanda.descricao,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 128.dp, y = 45.dp)
                    .width(210.dp)
            )
            if (demanda.data == null || demanda.data == "") {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 21.dp, y = 17.dp)
                        .width(86.dp)
                        .height(93.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(id = R.drawable.rectangle_71__1_),
                    contentDescription = "Botao de Voltar"
                )
            } else {
                demanda.data?.let {
                    Base64Image(
                        base64String = it,
                        Modifier
                            .width(86.dp)
                            .align(Alignment.TopStart)
                            .offset(x = 21.dp, y = 17.dp)
                            .height(93.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) }
            }
            Text(
                text = categoria(demanda.categoria),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 128.dp, y = 16.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(bottomStart = 38.dp, bottomEnd = 38.dp))
                .background(Color(0xFF4CAF50))
        ) {
            Text(
                text = "Finalizada",
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 23.dp)
            )
            Image(
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 23.dp)
                    .clickable {
                        if (!showDemandaCompleta) {
                            showDemandaCompleta = true
                        } else {
                            showDemandaCompleta = false
                        }
                    },
                painter = painterResource(id = R.drawable.visualizar_1),
                contentDescription = "Botao de Voltar"
            )
        }
    }

    if (showDemandaCompleta) {
        Box(
            modifier = Modifier
                .height(400.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(lightGray)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { showDemandaCompleta = false }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Fechar",
                        tint = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                ) {
                    Text(
                        text = "Categoria - ${categoria(demanda.categoria)}",
                        fontSize = 25.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        if (demanda.data === null || demanda.data === "AA==") {
                            Image(
                                painter = painterResource(R.drawable.img_geral_default),
                                contentDescription = "imagem da demanda",
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray)
                            )
                        } else {
                            demanda.data?.let {
                                Base64Image(
                                    base64String = it,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(13.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Cód. Demanda: ${demanda.id}",
                        fontSize = 12.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (demanda.descricao != null) "${demanda.descricao}" else "Parece que esta demanda não tem uma descrição...",
                        fontSize = 20.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Clip,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}