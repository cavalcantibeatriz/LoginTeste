package com.example.mobilefaztudo.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundRegister
import com.example.faztudo_mb.ui.theme.screens.components.InputWithIcon
import com.example.faztudo_mb.ui.theme.screens.components.imagem
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.Category
import com.example.mobilefaztudo.ui.theme.laranjaBtn
import com.example.mobilefaztudo.viewModel.CadastroContratanteViewModel
import com.example.mobilefaztudo.viewModel.CadastroPrestadorViewModel

@Composable
fun CadastroScreenEtapa1(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6D90D1))
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top image
        Image(
            painter = painterResource(id = R.drawable.icone_cadastro),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.etapa_1_cadastro),
            contentDescription = "Top Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = "Informações de trabalho",
            modifier = Modifier.height(50.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("cadastro2C")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = laranjaBtn,
                contentColor = Color.White
            )
        ) {
            Text(
                "Quero contratar", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("cadastro2P")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = laranjaBtn,
                contentColor = Color.White
            )
        ) {
            Text(
                "Quero trabalhar",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun CadastroContratanteEtapa2(
    cadastroContratanteViewModel: CadastroContratanteViewModel,
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var dt_nascimento by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("RUA 1") }
    var city by remember { mutableStateOf("teste1") }
    var state by remember { mutableStateOf("SP") }
    var phone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var proUser by remember { mutableStateOf(false) }

    var showModalSuccess by remember { mutableStateOf(false) }
    var showModalError by remember { mutableStateOf(false) }
    var exibirCamposSenha by remember { mutableStateOf(false) }
    var exibirCamposIniciais by remember { mutableStateOf(true) }

    Log.d(
        "CADASTRO",
        "Dados a serem enviados::: $name, $lastName, $email, $cpf, $cep, $dt_nascimento, $logradouro, $city, $state, $phone, $senha, $proUser"
    )

    if (exibirCamposIniciais) {
        BackgroundRegister(backgroundImageResId = imagem)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF6D90D1))
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.etapa_2_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = "Suas informações",
                modifier = Modifier.height(50.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputWithIcon(
                icon = Icons.Default.Person,
                placeholder = "Nome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = name,
                onValueChange = { name = it }
            )
            InputWithIcon(
                icon = Icons.Default.Face,
                placeholder = "Sobrenome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = lastName,
                onValueChange = { lastName = it }
            )
            InputWithIcon(
                icon = Icons.Default.Email,
                placeholder = "E-mail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = email,
                onValueChange = { email = it }
            )
            InputWithIcon(
                icon = Icons.Default.AddCircle,
                placeholder = "CPF",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = cpf,
                onValueChange = { cpf = it }
            )
            InputWithIcon(
                icon = Icons.Default.LocationOn,
                placeholder = "CEP",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = cep,
                onValueChange = { cep = it }
            )
            InputWithIcon(
                icon = Icons.Default.DateRange,
                placeholder = "Data de nascimento",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = dt_nascimento,
                onValueChange = { dt_nascimento = it }
            )
            InputWithIcon(
                icon = Icons.Default.Call,
                placeholder = "Telefone",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = phone,
                onValueChange = { phone = it }
            )

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    exibirCamposIniciais = false
                    exibirCamposSenha = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = laranjaBtn,
                    contentColor = Color.White
                )
            ) {
                Text("Continuar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }


        }

    }

    if (exibirCamposSenha) {
        BackgroundRegister(backgroundImageResId = imagem)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF6D90D1))
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.etapa_3_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = "Defina sua senha",
                modifier = Modifier.height(50.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputWithIcon(
                icon = Icons.Default.Lock,
                placeholder = "Senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = senha,
                onValueChange = { senha = it }
            )
            InputWithIcon(
                icon = Icons.Default.Lock,
                placeholder = "Confirme a senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = "",
                onValueChange = { }
            )
            Spacer(modifier = Modifier.height(5 .dp))
            Box(
                modifier = Modifier
                    .height(290.dp)
//                    .border(2.dp, Color.Red)  // Adiciona uma borda cinza de 1 dp ao redor do Box
//                    .padding(3.dp),  // Adiciona um padding interno para que o texto não fique colado na borda
            ){
                Text(
                    text =  "A senha deve conter letras, números e caracteres especiais!\n\n- Evite sequências númericas\n- Evite sua data de nascimento\n- Evite seu telefone",
                    modifier = Modifier
                        .height(150.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Left
                    )
                )
            }


            Button(
                onClick = {
                    Log.d("CADASTRO", "CLIQUEI NO BOTÃO")
                    cadastroContratanteViewModel.registerContractor(name, lastName,cpf,dt_nascimento,cep,logradouro,state,city,phone,email,senha,proUser) { onResult ->
                        if (onResult) {
                            showModalSuccess = true
                        } else {
                            showModalError = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = laranjaBtn,
                    contentColor = Color.White
                )
            ) {
                Text("Cadastrar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }
        }

    }
    if (showModalSuccess) {
        Log.d("CADASTRO", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccess = false
            },
            title = { Text("Eba!") },
            text = { Text("Parece que seu cadastro foi realizado com sucesso!\n\nVamos te direcionar para o login!") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccess = false
                    navController.navigate("login")
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
                showModalSuccess = false
            },
            title = { Text("Oops") },
            text = { Text("Epa! Parece que houve algum erro ao te cadastrar :(\n\nTente novamente em alguns instantes.") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccess = false
                    navController.navigate("cadastro1")
                }) {
                    Text("OK")
                }
            }
        )
    }

}


@Composable
fun CadastroPrestadorEtapa2(
    cadastroPrestadorViewModel: CadastroPrestadorViewModel = viewModel(),
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var dt_nascimento by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("RUA 1") }
    var city by remember { mutableStateOf("teste1") }
    var state by remember { mutableStateOf("SP") }
    var phone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(Category(id = 6, name = "Todos")) }

    var exibirCamposSenha by remember { mutableStateOf(false) }
    var exibirCamposIniciais by remember { mutableStateOf(false) }
    var exibirCategorias by remember { mutableStateOf(true) }

    Log.d("CADASTRO", "Dados a serem enviados::: $name, $lastName, $email, $cpf, $cep, $dt_nascimento, $logradouro, $city, $state, $phone, $senha, $category")
    Log.d("CADASTRO", "SELECTED_CATEGORY::: $category")

    var showModalSuccess by remember { mutableStateOf(false) }
    var showModalError by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    if (exibirCategorias) {
        BackgroundRegister(backgroundImageResId = imagem)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF6D90D1))
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.etapa_1_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = "Selecione sua categoria",
                modifier = Modifier.height(50.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .height(390.dp)
                    .border(3.dp, Color.Red)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween  // Espaçamento entre os botões
                ) {
                    Button(
                        onClick = { Category(id = 1, name = "Mecânica") },
                         modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1A58D), // Cor laranja
                            contentColor = Color.Black          // Cor do texto
                        )
                    ) {
                        Text(
                            text = "Mecânica", style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Button(
                        onClick = { Category(id = 2, name = "Hidráulica") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1A58D), // Cor laranja
                            contentColor = Color.Black          // Cor do texto
                        )
                    ) {
                        Text(
                            text = "Hidráulica", style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Button(
                        onClick = { Category(id = 3, name = "Limpeza") },
                        modifier = Modifier
                            .fillMaxWidth().padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1A58D), // Cor laranja
                            contentColor = Color.Black          // Cor do texto
                        )
                    ) {
                        Text(
                            text = "Limpeza",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Button(
                        onClick = { Category(id = 4, name = "Elétrica") },
                        modifier = Modifier
                            .fillMaxWidth().padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1A58D), // Cor laranja
                            contentColor = Color.Black          // Cor do texto
                        )
                    ) {
                        Text(
                            text = "Elétrica",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Button(
                        onClick = { Category(id = 5, name = "Obras") },
                        modifier = Modifier
                            .fillMaxWidth().padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1A58D), // Cor laranja
                            contentColor = Color.Black          // Cor do texto
                        )
                    ) {
                        Text(
                            text = "Obras Gerais",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Button(
                        onClick = { Category(id = 6, name = "Todos") },
                        modifier = Modifier
                            .fillMaxWidth().padding(2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF1A58D), // Cor laranja
                            contentColor = Color.Black          // Cor do texto
                        )
                    ) {
                        Text(
                            text = "Todos",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
            Button(
                onClick = {
                    exibirCamposIniciais = true
                    exibirCamposSenha = false
                    exibirCategorias = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = laranjaBtn,
                    contentColor = Color.White
                )
            ) {
                Text("Continuar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }
        }
    }

    if (exibirCamposIniciais) {
        BackgroundRegister(backgroundImageResId = imagem)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF6D90D1))
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.etapa_2_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = "Suas informações",
                modifier = Modifier.height(50.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            InputWithIcon(
                icon = Icons.Default.Person,
                placeholder = "Nome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = name,
                onValueChange = { name = it }
            )
            InputWithIcon(
                icon = Icons.Default.Face,
                placeholder = "Sobrenome",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = lastName,
                onValueChange = { lastName = it }
            )
            InputWithIcon(
                icon = Icons.Default.Email,
                placeholder = "E-mail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = email,
                onValueChange = { email = it }
            )
            InputWithIcon(
                icon = Icons.Default.AddCircle,
                placeholder = "CPF",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = cpf,
                onValueChange = { cpf = it }
            )
            InputWithIcon(
                icon = Icons.Default.LocationOn,
                placeholder = "CEP",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = cep,
                onValueChange = { cep = it }
            )
            InputWithIcon(
                icon = Icons.Default.DateRange,
                placeholder = "Data de nascimento",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = dt_nascimento,
                onValueChange = { dt_nascimento = it }
            )
            InputWithIcon(
                icon = Icons.Default.Call,
                placeholder = "Telefone",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                value = phone,
                onValueChange = { phone = it }
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    exibirCamposIniciais = false
                    exibirCamposSenha = true
                    exibirCategorias = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = laranjaBtn,
                    contentColor = Color.White
                )
            ) {
                Text("Continuar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }
        }
    }

    if (exibirCamposSenha){
        BackgroundRegister(backgroundImageResId = imagem)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF6D90D1))
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.icone_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.etapa_3_cadastro),
                contentDescription = "Top Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(
                text = "Defina sua senha",
                modifier = Modifier.height(50.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            InputWithIcon(
                icon = Icons.Default.Lock,
                placeholder = "Senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = senha,
                onValueChange = { senha = it }
            )
            InputWithIcon(
                icon = Icons.Default.Lock,
                placeholder = "Confirme a senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = "",
                onValueChange = {  }
            )
            Box(
                modifier = Modifier
                    .height(290.dp)
//                    .border(2.dp, Color.Red)  // Adiciona uma borda cinza de 1 dp ao redor do Box
//                    .padding(3.dp),  // Adiciona um padding interno para que o texto não fique colado na borda
            ){
                Text(
                    text =  "A senha deve conter letras, números e caracteres especiais!\n\n- Evite sequências númericas\n- Evite sua data de nascimento\n- Evite seu telefone",
                    modifier = Modifier
                        .height(150.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Left
                    )
                )
            }

            Button(
                onClick = {
                    Log.d("CADASTRO", "CLIQUEI NO BOTÃO")
                    cadastroPrestadorViewModel.registerProvider(
                        name,
                        lastName,
                        cpf,
                        dt_nascimento,
                        cep,
                        logradouro,
                        state,
                        city,
                        phone,
                        email,
                        senha,
                        category
                    ) { onResult ->
                        if (onResult) {
                            showModalSuccess = true
                        } else {
                            showModalError = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = laranjaBtn,
                    contentColor = Color.White
                )
            ) {
                Text("Cadastrar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }
        }
    }
    if (showModalSuccess) {
        Log.d("CADASTRO", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccess = false
            },
            title = { Text("Eba!") },
            text = { Text("Parece que seu cadastro foi realizado com sucesso!\n\nVamos te direcionar para o login!") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccess = false
                    navController.navigate("login")
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
                showModalSuccess = false
            },
            title = { Text("Oops") },
            text = { Text("Epa! Parece que houve algum erro ao te cadastrar :(\n\nTente novamente em alguns instantes.") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccess = false
                    navController.navigate("cadastro1")
                }) {
                    Text("OK")
                }
            }
        )
    }
}
