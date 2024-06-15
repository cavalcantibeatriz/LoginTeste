package com.example.mobilefaztudo.view.TelasPerfil

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPerfilPrestador
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarPrestador
import com.example.mobilefaztudo.ui.theme.components_new.PhotoProfile

@Composable
fun PerfilPrestadorScreen(
    navController: NavController, sharedPreferencesHelper: SharedPreferencesHelper
) {
    //NÃO APAGARRR
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        // Aqui para lidar com a URI da imagem selecionada
    }

    var showEditInfo by remember { mutableStateOf(false) }
    var showEditSenha by remember { mutableStateOf(false) }
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    var confirmSenha by remember { mutableStateOf("") }
    var rua by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var showEditGaleria by remember { mutableStateOf(false) }
    var showConfirmEditGaleria by remember { mutableStateOf(false) }
    var showAddGaleria by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundPerfilPrestador()
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
                    navController=navController,
                    sharedPreferencesHelper= sharedPreferencesHelper)
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
                        fontSize = 28.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    PhotoProfile()
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "José Mário da Silva",
                        fontSize = 25.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 25.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Especialista em Elétrica",
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
                ){
                    Text(
                        text = "Entrou na plataforma há 3 dias",
                        fontSize = 13.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.ExtraLight,
                            fontStyle = FontStyle.Italic,
                            fontSize = 13.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(onClick = { showEditInfo = true}) {
                        Text(text = "Editar informações")
                    }
                    Button(onClick = { showEditSenha = true }) {
                        Text(text = "Redefinir senha")
                    }
                    Button(onClick = { showEditGaleria = true }) {
                        Text(text = "Editar galeria")
                    }
                }
            }
            NavBarPrestador(
                sharedPreferencesHelper = sharedPreferencesHelper,
                navController = navController,
                "Person"
            )
        }
    }

    if (showEditInfo){
        var selectedOption by remember { mutableStateOf("Selecione uma categoria") }
        val options = listOf("Mecânica","Hidráulica","Limpeza", "Elétrica","Obras", "Todos")
        var isDropdownExpanded by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showEditInfo = false
            },
            title = { Text("Edição de senha") },
            text = {
                Column(
                    modifier = Modifier
                        .height(350.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = cep,
                        onValueChange = {it -> cep = it},
                        label = { Text("CEP") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = rua,
                        onValueChange = {it -> rua = it},
                        label = { Text("Rua") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = logradouro,
                        onValueChange = {it -> logradouro = it},
                        label = { Text("Bairro") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = cidade,
                        onValueChange = {it -> cidade = it},
                        label = { Text("Cidade") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = estado,
                        onValueChange = {it -> estado = it},
                        label = { Text("Estado") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = telefone,
                        onValueChange = {it -> telefone = it},
                        label = { Text("Telefone") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        DropdownMenu(
                            modifier = Modifier
                                .width(270.dp),
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false }
                        ){
                            options.forEach { option ->
                                DropdownMenuItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        selectedOption = option
                                        isDropdownExpanded = false
                                    },
                                    text = { Text(option) }
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(CircleShape)
                                .clickable {
                                    isDropdownExpanded =
                                        !isDropdownExpanded
                                }
                                .background(color = Color(0xFFCDD3E0))
                        ){
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 33.dp)
                                    .requiredHeight(height = 32.dp)
                                    .clip(shape = CircleShape)
                                    .clickable {
                                        isDropdownExpanded =
                                            !isDropdownExpanded
                                    }
                                    .background(color = Color(0xff588aed))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown Arrow",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .align(alignment = Alignment.Center)
                                        .clickable {
                                            isDropdownExpanded =
                                                !isDropdownExpanded // Alternar entre expandido e não expandido
                                        }
                                )
                            }
                            Text(
                                text = selectedOption,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showEditInfo = false
                    showModalSucesso = true
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

    if (showEditSenha){
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
                        onValueChange = {it -> senha = it},
                        label = { Text("Senha") }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = confirmSenha,
                        onValueChange = {it -> confirmSenha = it},
                        label = { Text("Confirme a senha") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    showEditSenha = false
                    showModalSucesso = true
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

    if (showModalSucesso){
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

    if (showModalErro){
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

    if (showEditGaleria){
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showEditGaleria = false
            },
            title = { Text("Galeria - Pedro") },
            text = {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.height(5.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2), // Número de colunas
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(680.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            items(10) {
                                Image(
                                    painter = painterResource(R.drawable.img_profile_default),
                                    contentDescription = "Imagem $it",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(140.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                                Image(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clickable {showConfirmEditGaleria = true},
                                    painter = painterResource(id = R.drawable.group),
                                    contentDescription = "Imagem Menor",
                                    alignment = Alignment.TopEnd
                                )
                            }
                        }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showEditGaleria = false
                    showAddGaleria = true
                }) {
                    Text("Adicionar imagem")
                }
            },
            dismissButton = {
                Button(onClick = { showEditGaleria = false }) {
                    Text(text = "Voltar ao perfil")
                }
            }
        )
    }

    if (showConfirmEditGaleria){
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showConfirmEditGaleria = false
            },
            title = { Text("Atenção!") },
            text = { Text("Deseja mesmo deletar esta imagem de sua galeria?") },
            confirmButton = {
                Button(onClick = {
                    showConfirmEditGaleria = false
                    showModalSucesso = true
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showConfirmEditGaleria = false}) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (showAddGaleria){
        AlertDialog(
            onDismissRequest = { showAddGaleria = false},
            title = {
                Text(
                    text = "Adicionar foto de perfil",
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
            },
            text = {
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .background(color = Color(0xFFCDD3E0))
                    ){
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 33.dp)
                                .requiredHeight(height = 32.dp)
                                .clip(shape = CircleShape)
                                .clickable {
                                    galleryLauncher.launch("image/*")
                                }
                                .background(color = Color(0xff588aed))
                        ){
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Arrow - Down 2",
                                tint = Color.Black,
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                            )
                        }
                        Text(
                            text = "Adicione uma imagem",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                }
            },
            confirmButton = {
                Button(
                    onClick = { showModalSucesso = true
                        showAddGaleria = false
                    }
                ) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showAddGaleria = false }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

}