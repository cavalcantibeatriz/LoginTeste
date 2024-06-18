package com.example.mobilefaztudo.ui.theme.components_new

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.faztudo_mb.ui.theme.screens.components_new.Base64Image
import com.example.mobilefaztudo.api.DemandaInteresse
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.viewModel.Contratante.AceitarInteresseViewModel
import com.example.mobilefaztudo.viewModel.EnviarEmailViewModel
import com.example.mobilefaztudo.viewModel.Contratante.NegarInteresseViewModel

@Composable
fun PrestadorNotificationCard(
    notification : DemandaInteresse,
    negarInteresseViewModel: NegarInteresseViewModel = viewModel(),
    aceitarInteresseViewModel : AceitarInteresseViewModel = viewModel(),
    enviarEmailViewModel : EnviarEmailViewModel = viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper
) {
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

    var showModalAceitar by remember { mutableStateOf(false) }
    var showModalNegar by remember { mutableStateOf(false) }
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }

    var to by remember { mutableStateOf("beatrizhellen2009@gmail.com") }
    var subject by remember { mutableStateOf("Opa, alguém aceitou seu interesse \uD83E\uDD29") }
    var body by remember { mutableStateOf("Oie, parece que um contratante aceitou seu interesse! Você pode liberar seu contato agora pelo site ou app...\n" +
            "\n✅ Demanda: ${notification.post.descricao}" +
            "\n✅ Seu interesse: ${notification.mensagem}" +
            "\n✅ Categoria: ${categoria(notification.post.categoria)}" +
            "\n\nContamos com você! FazTudo.com") }

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
            text = notification.mensagem ,
            color = Color.Black,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 132.dp, y = 45.dp)
                .requiredWidth(width = 200.dp)
                .height(70.dp)
                .verticalScroll(rememberScrollState())
        )
        if (notification.post.data == "AA==" || notification.post.data == null ){
            Image(
                painter = painterResource(id = com.example.mobilefaztudo.R.drawable.img_geral_default ),
                contentDescription = "Rectangle 70",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 21.dp, y = 17.dp)
                    .requiredWidth(width = 86.dp)
                    .requiredHeight(height = 93.dp)
            )
        }else{
            notification.post.data?.let{
                Base64Image(
                    base64String = it,
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 21.dp, y = 17.dp)
                        .requiredWidth(width = 86.dp)
                        .requiredHeight(height = 93.dp)
                )
            }
        }
        Text(
            text = "${notification.prestador.name} enviou um interesse!",
            color = Color.Black,
            textAlign = TextAlign.Start,
            maxLines =1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 17.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .width(200.dp)
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
                    try {
                        aceitarInteresseViewModel.aceitarInteresse(notification.id){onResult ->
                            if (onResult) {
                                showModalAceitar = false
                                showModalSucesso = true
                                enviarEmailViewModel.enviarEmail(to,subject,body){onResult ->
                                    Log.d("EMAIL", "Email enviado com sucesso -> Aceite demanda")
                                }
                            }else{
                                showModalAceitar = false
                                showModalErro = true
                            }
                        }
                    }catch (e:Exception){
                        Log.d("ACEITAR", "Erro ao ACEITAR::: ${e}")
                    }
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
                    try {
                        negarInteresseViewModel.negarInteresse(notification.id){onResult ->
                            if (onResult) {
                                showModalNegar = false
                                showModalSucesso = true
                            }else{
                                showModalNegar = false
                                showModalErro = true
                            }
                        }
                    }catch (e:Exception){
                        Log.d("NEGAR", "Erro ao NEGAR::: ${e}")
                    }
                }) {
                    Text("Negar")
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
