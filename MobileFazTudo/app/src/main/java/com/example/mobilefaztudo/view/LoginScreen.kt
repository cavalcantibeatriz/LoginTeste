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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundRegister
import com.example.faztudo_mb.ui.theme.screens.components.InputWithIcon
import com.example.faztudo_mb.ui.theme.screens.components.imagem
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.ui.theme.laranjaBtn
import com.example.mobilefaztudo.ui.theme.salmaoBtn
import com.example.mobilefaztudo.ui.theme.salmaoRosadoBtn
import com.example.mobilefaztudo.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(), navController: NavController
) {
    // Define os estados dos campos de email e senha
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

//    var email = "eli@gmail.com"
//    var senha = "123456"

    Log.d("LOGIN", "$email + $senha")

    var showModal by remember { mutableStateOf(false) }
    BackgroundRegister(backgroundImageResId = imagem)

    // Layout da tela de login
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
            alignment = Alignment.Center,

            )

        Spacer(modifier = Modifier.height(50.dp))

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
        ) {
            Text(
                "Entrar",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            )
        }

        Button(
            onClick = {
                Log.d("LOGIN", "CLIQUEI NO BOTÃO CADASTRO")
                navController.navigate("cadastro1")
            },
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 16.dp),
             colors = ButtonDefaults.buttonColors(
                containerColor = salmaoRosadoBtn
        )) {
            Text(
                "Não tenho uma conta",
                style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
            )
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
