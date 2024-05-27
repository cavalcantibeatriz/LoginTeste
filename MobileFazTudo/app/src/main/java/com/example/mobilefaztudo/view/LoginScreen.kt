package com.example.mobilefaztudo.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundRegister
import com.example.faztudo_mb.ui.theme.screens.components.imagem
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.ui.theme.laranjaBtn
import com.example.mobilefaztudo.ui.theme.salmaoRosadoBtn
import com.example.mobilefaztudo.viewModel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), navController: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    Log.d("LOGIN", "$email + $senha")
    var showModalSuccess by remember { mutableStateOf(false) }
    var showModalError by remember { mutableStateOf(false) }

    BackgroundRegister(backgroundImageResId = imagem)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logofaztudo___copia_1),
            contentDescription = null, // A descrição do conteúdo é opcional
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                Log.d("LOGIN", "CLIQUEI NO BOTÃO")
                loginViewModel.login(email, senha) { onResult ->
                    if (onResult) {
                        showModalSuccess = true
                    } else {
                        showModalError = true
                    }
                }
            },
            modifier = Modifier
                .width(250.dp)
                .height(100.dp)
                .padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = laranjaBtn,
                contentColor = Color.White
            )
        ) { Text("Entrar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)) }
        Button(
            onClick = {
                Log.d("LOGIN", "CLIQUEI NO BOTÃO CADASTRO")
                navController.navigate("cadastro1")
            },
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = salmaoRosadoBtn)
        ) {
            Text(
                "Não tenho uma conta",
                style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
    if (showModalSuccess) {
        Log.d("LOGIN", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccess = false
            },
            title = { Text("Mensagem") },
            text = { Text("Eba! Login realizado") },
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
        Log.d("CADASTRO", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalError = false
            },
            title = { Text("Oops") },
            text = { Text("Epa! Parece que houve algum erro ao tentar entrar em sua conta :(\n\nTente novamente em alguns instantes.") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalError = false
                    navController.navigate("cadastro1")
                }) {
                    Text("OK")
                }
            }
        )
    }
}
