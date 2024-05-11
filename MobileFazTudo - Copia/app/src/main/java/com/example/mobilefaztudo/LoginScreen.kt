package com.example.mobilefaztudo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.material3.Snackbar


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    // Define os estados dos campos de email e senha
    var email by remember  { mutableStateOf("") }
    var senha by remember  { mutableStateOf("") }

//    var email = "eli@gmail.com"
//    var senha = "123456"

    Log.d("LOGIN", "$email + $senha")

    var showModal by remember { mutableStateOf(false) }

    // Layout da tela de login
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Campo de senha
        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )


        // Botão de login
        Button(
            onClick = {
                Log.d("LOGIN", "CLIQUEI NO BOTÃO")
                loginViewModel.login(email, senha){ onResult ->
                    // Mostrar modal se o login for bem-sucedido
                    showModal = onResult
                }},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Entrar")
        }
    }

    if (showModal) {
        Log.d("LOGIN", "CHAMOU O MODAL")
            AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModal = false
            },
            title = { Text("Mensagem") },
            text = { Text("Eba! Login realizado") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModal = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}

