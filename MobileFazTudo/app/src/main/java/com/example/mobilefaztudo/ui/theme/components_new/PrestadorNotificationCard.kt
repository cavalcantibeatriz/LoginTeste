package com.example.mobilefaztudo.ui.theme.components_new

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrestadorNotificationCard() {

    var showModalAceitar by remember { mutableStateOf(false) }
    var showModalNegar by remember { mutableStateOf(false) }
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .width(355.dp)
            .height(167.dp)
    ) {
        Box(
            modifier = Modifier
                .width(355.dp)
                .height(128.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                )
        )
        Text(
            text = "Enviou Interesse em sua postagem “Ajuda com a pia[...]”",
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 132.dp, y = 45.dp)
                .requiredWidth(width = 210.dp)
        )
        Image(
            painter = painterResource(id = com.example.mobilefaztudo.R.drawable.img ),
            contentDescription = "Rectangle 70",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 21.dp, y = 17.dp)
                .requiredWidth(width = 86.dp)
                .requiredHeight(height = 93.dp)
        )
        Text(
            text = "Tomas Costa",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 132.dp, y = 13.dp)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 39.dp, y = 128.dp)
                .requiredWidth(width = 277.dp)
                .requiredHeight(height = 39.dp)
        ) {
            Button(
                onClick = { showModalNegar = true},
                colors = ButtonDefaults.buttonColors(Color(0xff992323)),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                modifier = Modifier
                    .requiredWidth(106.dp)
                    .requiredHeight(39.dp)
            ) {
                Text(
                    text = "Negar",
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
            Button(
                onClick = { showModalAceitar = true },
                colors = ButtonDefaults.buttonColors(Color(0xff239960)),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 171.dp, y = 0.dp)
                    .requiredWidth(106.dp)
                    .requiredHeight(39.dp)
            ) {
                Text(
                    text = "Aceitar",
                    color = Color.White,
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    if (showModalAceitar) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalAceitar = false
            },
            title = { Text("Eba!") },
            text = { Text("Você aceitou o interesse na demanda: !") },
            confirmButton = {
                Button(onClick = {
                    showModalAceitar = false
                    showModalSucesso = true
//                    showModalSucesso = true
                }) {
                    Text("Aceitar")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showModalNegar = false
                }) {
                    Text("Cancelar")
                }
            }

        )
    }

    if (showModalNegar) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                showModalNegar = false
            },
            title = { Text("Poxa!") },
            text = { Text("Tem certeza que deseja negar este interesse?") },
            confirmButton = {
                Button(onClick = {
                    showModalNegar = false
                    showModalSucesso = true
                }) {
                    Text("Negar")
                }
            },
            dismissButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalNegar = false
                }) {
                    Text("Cancelar")
                }
            }

        )
    }

    if (showModalSucesso) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                showModalSucesso = false
            },
            title = { Text("Sucesso!") },
            text = { Text("Feito, ação realizada com sucesso!") },
            confirmButton = {
                Button(onClick = {
                    showModalSucesso = false
                    //showModalAceitar = false
//                    showModalNegar = false
                }) {
                    Text("Ok")
                }
            }

        )
    }

    if (showModalErro) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                showModalErro = false
            },
            title = { Text("Ooops!") },
            text = { Text("Parece que houve uma falha ao realizar a ação :(") },
            confirmButton = {
                Button(onClick = {
                    showModalErro = false
                    //showModalSucesso = false
//                    showModalNegar = false
                }) {
                    Text("Ok")
                }
            }

        )
    }

}

@Preview(widthDp = 355, heightDp = 167)
@Composable
private fun Group19Preview() {
    PrestadorNotificationCard()
}
