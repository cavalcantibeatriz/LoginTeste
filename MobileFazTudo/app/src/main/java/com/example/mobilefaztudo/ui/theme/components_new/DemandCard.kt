package com.example.faztudo_mb.ui.theme.screens.components_new

import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.Demanda
import com.example.mobilefaztudo.api.MensagemRequest
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.viewModel.EnviarMensagensViewModel
import com.google.gson.Gson

@Composable
fun DemandCard(
    modifier: Modifier = Modifier,
    demanda: Demanda,
    sharedPreferencesHelper: SharedPreferencesHelper,
) {
    val lightGray = Color(0xFFD3D3D3)

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

    var showModalInserirMensagem by remember { mutableStateOf(false) }
    var mensagemTexto by remember { mutableStateOf("") }
    var showModalError by remember { mutableStateOf(false) }
    var showModalSuccess by remember { mutableStateOf(false) }
    var showDemandaCompleta by remember { mutableStateOf(false) }
    var showDemandaCard by remember { mutableStateOf(true) }

    fun toggleImage() {
        Log.d("INTERESSE", "chamou o toggle")
    }


    if (showDemandaCard) {
        Box(
            modifier = modifier
                .width(350.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                )
                .padding(top = 20.dp, bottom = 17.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomEnd = 20.dp,
                            bottomStart = 20.dp
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (demanda.data === null) {
                    Image(
                        modifier = modifier,
                        painter = painterResource(id = R.drawable.rectangle_71__1_),
                        contentDescription = "Botao de Voltar"
                    )
                } else {
                    demanda.data?.let { Base64Image(base64String = it, modifier.width(100.dp)) }
                }

                Spacer(modifier = modifier.width(20.dp))
                Column(modifier = modifier) {
                    Text(
                        text = categoria(demanda.categoria),
                        fontWeight = FontWeight.Bold,
                        //fontFamily = FontFamily(Font(R.font.poppins))
                        fontSize = 22.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = demanda.descricao,
                        modifier = modifier
                            .width(180.dp)
                            .height(50.dp),
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Clip
                    )
                    Row(
                        modifier = modifier
                            .padding(start = 140.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_60),
                            contentDescription = "Botao de ENVIAR",
                            modifier = Modifier.clickable {
                                if (!showModalInserirMensagem){
                                    showModalInserirMensagem = true
                                }else{
                                    showModalInserirMensagem =false
                                }
                            }
                        )
                        Spacer(modifier = modifier.width(10.dp))
                        Image(
                            modifier = Modifier.clickable {
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
            }
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
                                    modifier = modifier
                                        .size(200.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    }
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

    if (showModalInserirMensagem) {
        Log.d("MENSAGEM", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalInserirMensagem = false
            },
            title = { Text("Eba!") },
            text = {
                Column {
                    Text("Parece que você quer demonstrar interesse nesta demanda!")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = mensagemTexto,
                        onValueChange = { mensagemTexto = it },
                        label = { Text("Digite sua mensagem") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    toggleImage()
                    showModalInserirMensagem = false
                }) {
                    Text("Enviar")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showModalInserirMensagem = false
                }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showModalSuccess) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccess = false
            },
            title = { Text("Eba!") },
            text = { Text("Você mandou interesse na demanda: ${demanda.id}!") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccess = false
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showModalError) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalError = false
            },
            title = { Text("Oops") },
            text = { Text("Parece que algo deu errado :(") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalError = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}


