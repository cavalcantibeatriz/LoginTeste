package com.example.mobilefaztudo.view.TelasPerfil

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPerfilContratante
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.ui.theme.components_new.PhotoProfile
import com.example.mobilefaztudo.viewModel.AtualizarPerfilViewModel
import com.example.mobilefaztudo.viewModel.AtualizarSenhaViewModel
import com.example.mobilefaztudo.viewModel.Contratante.AtualizarInfoContratanteViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasUserViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PerfilContratanteScreen(
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper,
    atualizarImgPerfilViewModel: AtualizarPerfilViewModel = viewModel(),
    listDemandasUserViewModel: ListDemandasUserViewModel = viewModel(),
    atualizarSenhaViewModel: AtualizarSenhaViewModel = viewModel(),
    atualizarInfoContratanteViewModel: AtualizarInfoContratanteViewModel= viewModel()
) {
    var showEditInfo by remember { mutableStateOf(false) }
    var showEditSenha by remember { mutableStateOf(false) }
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalValidSenha by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    var confirmSenha by remember { mutableStateOf("") }

    var pcep = sharedPreferencesHelper.getCep()
    var pcity = sharedPreferencesHelper.getCity()
    var pstate = sharedPreferencesHelper.getState()
    var pphone = sharedPreferencesHelper.getPhone()
    var plogradouro = sharedPreferencesHelper.getLogradouro()

    var logradouro by remember { mutableStateOf(plogradouro) }
    var cep by remember { mutableStateOf(pcep) }
    var estado by remember { mutableStateOf(pstate) }
    var telefone by remember { mutableStateOf(pphone) }
    var cidade by remember { mutableStateOf(pcity) }

    var nomeSalvo = sharedPreferencesHelper.getNome()
    var sobrenomeSalvo = sharedPreferencesHelper.getSobrenome()
    val dtCadastroString = sharedPreferencesHelper.getDataCadastro() // "2024-05-20T00:55:40.892604"
    val dtCadastro = LocalDateTime.parse(dtCadastroString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    val currentDate = LocalDateTime.now()
    val daysDifference = ChronoUnit.DAYS.between(dtCadastro, currentDate)
    val nameGeneric = "Usuário sem nome"
    val message = when {
        daysDifference == 0L -> "Entrou na plataforma hoje"
        daysDifference == 1L -> "Entrou na plataforma ontem"
        else -> "Entrou na plataforma há $daysDifference dias"
    }


    val listDemandas by listDemandasUserViewModel.listDemandasUser.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        listDemandasUserViewModel.listarDemandasUser()
    }
    val numeroDemandas = listDemandas.size

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundPerfilContratante()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                TopBar(
                    navController = navController,
                    sharedPreferencesHelper = sharedPreferencesHelper
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Notification Icon",
                        modifier = Modifier.size(27.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Seu perfil",
                        fontSize = 30.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    PhotoProfile(
                        navController,
                        sharedPreferencesHelper,
                        atualizarImgPerfilViewModel,
                        "C"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (nomeSalvo != null && sobrenomeSalvo != null) {
                        Text(
                            text = "$nomeSalvo $sobrenomeSalvo",
                            fontSize = 25.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 25.sp
                            )
                        )
                    } else {
                        Text(
                            text = nameGeneric,
                            fontSize = 25.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 25.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Demandas abertas - $numeroDemandas",
                        fontSize = 16.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = message,
                        fontSize = 13.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraLight,
                            fontStyle = FontStyle.Italic,
                            fontSize = 13.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { showEditInfo = true }) {
                        Text(text = "Editar informações")
                    }
                    Button(onClick = { showEditSenha = true }) {
                        Text(text = "Redefinir senha")
                    }
                }
            }
            NavBarContratante(
                sharedPreferencesHelper = sharedPreferencesHelper,
                navController = navController,
                initialState = "Person"
            )
        }
    }

    if (showEditInfo) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showEditInfo = false
            },
            title = { Text("Edição de senha") },
            text = {
                Column {

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = cep,
                        onValueChange = { it -> cep = it },
                        label = { Text("CEP") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = logradouro,
                        onValueChange = { it -> logradouro = it },
                        label = { Text("Bairro") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = cidade,
                        onValueChange = { it -> cidade = it },
                        label = { Text("Cidade") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = estado,
                        onValueChange = { it -> estado = it },
                        label = { Text("Estado") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = telefone,
                        onValueChange = { it -> telefone = it },
                        label = { Text("Telefone") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    try{
                        atualizarInfoContratanteViewModel.atualizarInformacoesContratante(cep,logradouro,estado,cidade,telefone){onResult ->
                            if (onResult){
                                Log.d("EditInfo", "SUCESSO")
                                showEditInfo = false
                                showModalSucesso = true
                            }else{
                                Log.d("EditInfo", "FALHA")
                                showEditInfo = false
                                showModalErro = true
                            }
                        }
                    }catch (e:Exception){
                        Log.d("EditInfo", "Exception::$e")
                        showModalErro = true
                    }
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                Button(onClick = { showEditInfo = false }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (showEditSenha) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showEditSenha = false
            },
            title = { Text("Edição de senha") },
            text = {
                Column {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = senha,
                        onValueChange = { it -> senha = it },
                        label = { Text("Senha") }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = confirmSenha,
                        onValueChange = { it -> confirmSenha = it },
                        label = { Text("Confirme a senha") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    try {
                        if (senha == confirmSenha) {
                            atualizarSenhaViewModel.atualizarSenha(senha) { onResult ->
                                if (onResult) {
                                    Log.d("RedefinirTela", "Sucesso")
                                    showModalSucesso = true
                                    showEditSenha = false
                                } else {
                                    Log.d("RedefinirTela", "falha")
                                    showModalErro = true
                                    showEditSenha = false
                                }
                            }
                        }else{
                            showModalValidSenha = true
                        }
                    } catch (e: Exception) {
                        Log.d("RedefinirTela", "Exception:::$e")
                        showModalErro = true
                        showEditSenha = false
                    }
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                Button(onClick = { showEditSenha = false }) {
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

    if (showModalValidSenha) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalValidSenha = false
            },
            title = { Text("Oops...") },
                text = { Text("Parece que suas senhas não coincidem :(") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalValidSenha = false
                    showEditSenha = true
                }) {
                    Text("OK")
                }
            }
        )
    }

}

