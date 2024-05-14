package com.example.mobilefaztudo.view

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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


    Log.d(
        "CADASTRO",
        "Dados a serem enviados::: $name, $lastName, $email, $cpf, $cep, $dt_nascimento, $logradouro, $city, $state, $phone, $senha, $proUser"
    )

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
            icon = Icons.Default.Person,
            placeholder = "Sobrenome",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = lastName,
            onValueChange = { lastName = it }
        )
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "E-mail",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = email,
            onValueChange = { email = it }
        )
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "CPF",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = cpf,
            onValueChange = { cpf = it }
        )
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "CEP",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = cep,
            onValueChange = { cep = it }
        )
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "Data de nascimento",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = dt_nascimento,
            onValueChange = { dt_nascimento = it }
        )
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "Telefone",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = phone,
            onValueChange = { phone = it }
        )
        InputWithIcon(
            icon = Icons.Default.Person,
            placeholder = "Senha",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = senha,
            onValueChange = { senha = it }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                Log.d("CADASTRO", "CLIQUEI NO BOTÃO")
                cadastroContratanteViewModel.registerContractor(
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
                    proUser
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
            Text("Continuar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
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
    var selectedCategory by remember { mutableStateOf(Category(id = 6, name = "Todos")) }

    val categories =
        listOf("Mecânica", "Hidráulica", "Limpeza", "Elétrica", "Obras Gerais", "Todos")
    var selectedCategoryName by remember { mutableStateOf(categories.first()) }
    fun getIdFromCategoryName(name: String): Int {
        return when (name) {
            "Mecânica" -> 1
            "Hidráulica" -> 2
            "Limpeza" -> 3
            "Elétrica" -> 4
            "Obras Gerais" -> 5
            "Todos" -> 6
            else -> 0
        }
    }

    Log.d(
        "CADASTRO",
        "Dados a serem enviados::: $name, $lastName, $email, $cpf, $cep, $dt_nascimento, $logradouro, $city, $state, $phone, $senha, $selectedCategory"
    )
    Log.d("CADASTRO", "SELECTED_CATEGORY::: $selectedCategoryName")

    var showModalSuccess by remember { mutableStateOf(false) }
    var showModalError by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Sobrenome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = cep,
            onValueChange = { cep = it },
            label = { Text("CEP") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = dt_nascimento,
            onValueChange = { dt_nascimento = it },
            label = { Text("Data de nascimento") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Telefone") },
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

        TextField(
            value = selectedCategoryName,
            onValueChange = {},
            label = { Text("Selecione a categoria") },
            readOnly = true,
            trailingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    "dropdown",
                    Modifier.clickable { expanded = !expanded })
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()  // Atualizando o tamanho
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            categories.forEach { label ->
                DropdownMenuItem(text = { label },
                    onClick = {
                        try {
                            selectedCategoryName = label
                            val categoryId = getIdFromCategoryName(label)
                            selectedCategory = Category(id = categoryId, name = label)
                            expanded = false
                        } catch (e: Exception) {
                            Log.e("DropdownMenu", "Erro ao selecionar categoria", e)
                            selectedCategoryName = "Erro na seleção"
                        }
                    })
            }
        }

        // Botão de login
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
                    selectedCategory
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
                .padding(vertical = 16.dp)
                .background(Color.Green)
        ) {
            Text("Cadastrar")
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
fun TelaCategorias() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6D90D1))
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
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
            text = "Defina sua categoria",
            modifier = Modifier.height(50.dp),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontStyle = FontStyle.Italic
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotaoIcone(Icons.Default.Lock, "Botão 1")
            Spacer(modifier = Modifier.width(10.dp))
            BotaoIcone(Icons.Default.Lock, "Botão 2")
            Spacer(modifier = Modifier.width(10.dp))
            BotaoIcone(Icons.Default.Lock, "Botão 3")
        }
        Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre as linhas de botões

        // Linha 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotaoIcone(Icons.Default.Lock, "Botão 4")
            BotaoIcone(Icons.Default.Lock, "Botão 5")
            BotaoIcone(Icons.Default.Lock, "Botão 6")
        }
        Button(
            onClick = {},
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
                "Cadastrar",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }





}


@Composable
fun TelaSenhaContratante() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF6D90D1))
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
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
            value = "",
            onValueChange = { }
        )
        InputWithIcon(
            icon = Icons.Default.Lock,
            placeholder = "Confirme sua senha",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            value = "",
            onValueChange = { }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
//                Log.d("CADASTRO", "CLIQUEI NO BOTÃO")
//                cadastroContratanteViewModel.registerContractor(
//                    name, lastName, cpf, dt_nascimento,cep, logradouro, state, city, phone, email, senha, proUser) {
//                        onResult ->
//                    if(onResult){
//                        showModalSuccess = true
//                    }else{
//                        showModalError =true
//                    }
//                }
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
                "Cadastrar",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),

                )
        }
    }
}


@Composable
fun BotaoIcone(icon: ImageVector, texto: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .height(80.dp)
            .width(80.dp), // Definindo um tamanho fixo para os botões
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, // Cor de fundo dos botões
            contentColor = Color.Black
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Ícone",  // Corrigindo para especificar o tipo de ícone
                tint = Color.Unspecified // Remove o tingimento automático para manter a cor original do ícone
            )

            Text(texto, style = TextStyle(fontSize = 12.sp))
        }
    }
}
