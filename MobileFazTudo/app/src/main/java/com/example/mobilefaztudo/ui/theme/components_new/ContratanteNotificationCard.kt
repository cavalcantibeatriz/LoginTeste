package com.example.mobilefaztudo.ui.theme.components_new

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.faztudo_mb.ui.theme.screens.components_new.Base64Image
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.DemandaInteresse
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.viewModel.EnviarEmailViewModel

@Composable
fun ContratanteNotificationCard(
    notification: DemandaInteresse,
    enviarEmailViewModel : EnviarEmailViewModel = viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper
) {
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }
    var showModalEnvio by remember { mutableStateOf(false) }

    var prestador = sharedPreferencesHelper.getNome()
    var telefone = sharedPreferencesHelper.getTelefone()
    var Snome = sharedPreferencesHelper.getSobrenome()
    var email = sharedPreferencesHelper.getEmail()

    var to by remember { mutableStateOf("beatrizhellen2009@gmail.com") }
    var subject by remember { mutableStateOf("Opa, alguém tem interesse em você...") }
    var body by remember { mutableStateOf("Oie, parece que  prestador ${prestador} liberou o contato para você! Entre em contatoo agora...\n" +
            "\n✅ Nome: ${prestador} ${Snome}" +
            "\n✅ Telefone: ${telefone}" +
            "\n✅ E-mail: ${email}" +
            "\n\nContamos com você! FazTudo.com") }

    Box(
        modifier = Modifier
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
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                ),
            verticalAlignment = Alignment.Top
        ) {
            if (notification.post.data == "AA==" || notification.post.data == null ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(93.dp),
                    painter = painterResource(id = R.drawable.img_profile_default),
                    contentDescription = "Botao de Voltar"
                )
            }else{
                notification.post.data?.let{
                    Base64Image(
                        base64String = it,
                        modifier = Modifier
                            .width(100.dp)
                            .height(93.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    , verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Libere seu contato agora!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Seu interesse foi aceito!\nMensagem: '${notification.mensagem}' ",
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .verticalScroll(rememberScrollState())
                )
                Row(
                    modifier = Modifier
                        .padding(start = 190.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_60),
                        contentDescription = "Botao de enviar dados",
                        modifier = Modifier
                            // Ajuste o tamanho conforme necessário
                            .clickable {
                                showModalEnvio = true
                            })
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

    if (showModalEnvio) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalEnvio = false
            },
            title = { Text("Atenção!") },
            text = { Text("Ao confirmar iremos liberar seu contato via e-mail para o contratante.") },
            confirmButton = {
                Button(onClick = {
                    enviarEmailViewModel.enviarEmail(to,subject,body){onResult ->
                        if (onResult == true){
                            showModalEnvio = false
                            showModalSucesso = true
                        }else{
                            showModalEnvio = false
                            showModalErro = true
                        }
                    }
                }) {
                    Text("Quero Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showModalEnvio = false }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (showModalSucesso) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSucesso = false
            },
            title = { Text("Eba!") },
            text = { Text("Ação realizada com sucesso!") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSucesso = false
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showModalErro) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalErro = false
            },
            title = { Text("Oops...") },
            text = { Text("Parece que alguma coisa deu errado...") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalErro = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}
