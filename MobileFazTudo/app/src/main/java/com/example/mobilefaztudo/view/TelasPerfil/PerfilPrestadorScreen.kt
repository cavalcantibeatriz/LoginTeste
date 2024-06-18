package com.example.mobilefaztudo.view.TelasPerfil

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPerfilPrestador
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarPrestador
import com.example.mobilefaztudo.ui.theme.components_new.PhotoProfile
import com.example.mobilefaztudo.viewModel.Ambos.AtualizarPerfilViewModel
import com.example.mobilefaztudo.viewModel.Ambos.AtualizarSenhaViewModel
import com.example.mobilefaztudo.viewModel.Prestador.AnexarGaleriaViewModel
import com.example.mobilefaztudo.viewModel.Contratante.AtualizarDescricaoViewModel
import com.example.mobilefaztudo.viewModel.GetGaleriaViewModel
import com.example.mobilefaztudo.viewModel.Prestador.AtualizarInfoPrestadorViewModel
import com.example.mobilefaztudo.viewModel.Prestador.DeleteGaleriaViewModel
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PerfilPrestadorScreen(
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper,
    atualizarImgPerfilViewModel: AtualizarPerfilViewModel = viewModel(),
    atualizarSenhaViewModel: AtualizarSenhaViewModel = viewModel(),
    atualizarInfoPrestadorViewModel: AtualizarInfoPrestadorViewModel = viewModel(),
    atualizarDescricaoViewModel: AtualizarDescricaoViewModel = viewModel(),
    getGaleriaViewModel: GetGaleriaViewModel = viewModel(),
    anexarGaleriaViewModel: AnexarGaleriaViewModel = viewModel(),
    deleteGaleriaViewModel: DeleteGaleriaViewModel= viewModel()
) {
    var showEditInfo by remember { mutableStateOf(false) }
    var showEditDescricao by remember { mutableStateOf(false) }
    var showEditSenha by remember { mutableStateOf(false) }
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }
    var showModalValidSenha by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    var confirmSenha by remember { mutableStateOf("") }
    var idImgIndex by remember { mutableStateOf(0) }


    var pcep = sharedPreferencesHelper.getCep()
    var pcity = sharedPreferencesHelper.getCity()
    var pstate = sharedPreferencesHelper.getState()
    var pphone = sharedPreferencesHelper.getPhone()
    var plogradouro = sharedPreferencesHelper.getLogradouro()
    var categoriaName = sharedPreferencesHelper.getCategoriaName()
    var pcategoriaId = sharedPreferencesHelper.getCategoriaId()
    var pcategoriaNome = sharedPreferencesHelper.getCategoriaName()
    var pdescricao = sharedPreferencesHelper.getDescricao()

    var cep by remember { mutableStateOf(pcep) }
    var estado by remember { mutableStateOf(pstate) }
    var logradouro by remember { mutableStateOf(plogradouro) }
    var telefone by remember { mutableStateOf(pphone) }
    var descricao by remember { mutableStateOf(pdescricao) }
    var cidade by remember { mutableStateOf(pcity) }
    var categoria by remember { mutableStateOf(pcategoriaNome) }
    var categoriaId by remember { mutableStateOf(pcategoriaId) }
    var showEditGaleria by remember { mutableStateOf(false) }
    var showConfirmEditGaleria by remember { mutableStateOf(false) }
    var showAddGaleria by remember { mutableStateOf(false) }

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

    fun categoriaTransform(nome: String): Int {
        var categoriaId = 0
        if (nome == "Mecânica") {
            categoriaId = 1
        }
        if (nome == "Hidráulica") {
            categoriaId = 2
        }
        if (nome == "Limpeza") {
            categoriaId = 3
        }
        if (nome == "Elétrica") {
            categoriaId = 4
        }
        if (nome == "Obras") {
            categoriaId = 5
        }
        if (nome == "Todos") {
            categoriaId = 6
        }
        return categoriaId
    }

    val listImagesGaleria by getGaleriaViewModel.listImagesGaleria.observeAsState(listOf())
    val idUser = sharedPreferencesHelper.getIdUser()

    LaunchedEffect(Unit) {
        getGaleriaViewModel.getGaleria(idUser)
    }

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
                ) {
                    PhotoProfile(
                        navController,
                        sharedPreferencesHelper,
                        atualizarImgPerfilViewModel,
                        "P"
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
                        text = "Especialista em $categoriaName",
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
                    if (pdescricao != "") {
                        Text(
                            text = "$pdescricao",
                            fontSize = 16.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        )
                    } else {
                        Text(
                            text = "Ops, parece que você ainda não tem uma descrição...",
                            fontSize = 16.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
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
                    Button(onClick = { showEditDescricao = true }) {
                        Text(text = "Editar descrição")
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

    if (showEditInfo) {
        var selectedOption by remember { mutableStateOf("Selecione uma categoria") }
        val options = listOf("Mecânica", "Hidráulica", "Limpeza", "Elétrica", "Obras", "Todos")
        var isDropdownExpanded by remember { mutableStateOf(false) }

        Log.d("Teste123", "${cep}")
        Log.d("Teste123", "${logradouro}")
        Log.d("Teste123", "${cidade}")
        Log.d("Teste123", "${telefone}")
        Log.d("Teste123", "${estado}")
        Log.d("Teste123", "${categoriaId}")
        Log.d("Teste123", "${categoriaName}")

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
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        selectedOption = option
                                        isDropdownExpanded = false
                                        categoriaName = option
                                        categoriaId = categoriaTransform(option)
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
                                    isDropdownExpanded = !isDropdownExpanded
                                }
                                .background(color = Color(0xFFCDD3E0))
                        ) {
                            Box(
                                modifier = Modifier
                                    .requiredWidth(width = 33.dp)
                                    .requiredHeight(height = 32.dp)
                                    .clip(shape = CircleShape)
                                    .clickable {
                                        isDropdownExpanded = !isDropdownExpanded
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
                    try {
                        atualizarInfoPrestadorViewModel.atualizarInformacoesPrestador(
                            cep, logradouro, estado, cidade, telefone, categoriaId, categoriaName
                        ) { onResult ->
                            if (onResult) {
                                Log.d("EditInfo", "SUCESSO")
                                showEditInfo = false
                                showModalSucesso = true
                            } else {
                                Log.d("EditInfo", "FALHA")
                                showEditInfo = false
                                showModalErro = true
                            }
                        }
                    } catch (e: Exception) {
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
                        } else {
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

    if (showEditDescricao) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showEditDescricao = false
            },
            title = { Text("Edição de senha") },
            text = {
                Column {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = descricao,
                        onValueChange = { it -> descricao = it },
                        label = { Text("Senha") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    try {
                        atualizarDescricaoViewModel.atualizarDescricao(descricao) { onResult ->
                            if (onResult) {
                                Log.d("Descricao", "Sucesso")
                                showModalSucesso = true
                                showEditDescricao = false
                                navController.navigate("PerfilPrestadorScreen")
                            } else {
                                Log.d("Descricao", "falha")
                                showModalErro = true
                                showEditDescricao = false
                            }
                        }
                    } catch (e: Exception) {
                        Log.d("Descricao", "Exception:::$e")
                        showModalErro = true
                        showEditDescricao = false
                    }
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                Button(onClick = { showEditDescricao = false }) {
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

    if (showEditGaleria) {
        fun decodeBase64ToBitmap(base64Str: String): Bitmap {
            val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }

        AlertDialog(
            onDismissRequest = { showEditGaleria = false },
            title = { Text("Galeria - Pedro") },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    if (listImagesGaleria.size == 0 || listImagesGaleria.isEmpty()) {
                        Text("Parece que você ainda não tem nenhuma foto na galeria")
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2), // Número de colunas
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(680.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            items(listImagesGaleria.size) { index ->
                                val imagemResponse = listImagesGaleria[index]
                                val bitmap = decodeBase64ToBitmap(imagemResponse.base64Data)
                                val idImg = imagemResponse.id
                                Image(
                                    painter = BitmapPainter(bitmap.asImageBitmap()),
                                    contentDescription = "Imagem ${imagemResponse.nome}",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .size(140.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                                Image(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clickable {
                                            idImgIndex = idImg
                                            showConfirmEditGaleria = true },
                                    painter = painterResource(id = R.drawable.imagelixo),
                                    contentDescription = "Imagem Menor",
                                    alignment = Alignment.TopEnd
                                )
                            }
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

    if (showConfirmEditGaleria) {
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showConfirmEditGaleria = false
            },
            title = { Text("Atenção!") },
            text = { Text("Deseja mesmo deletar esta imagem de sua galeria?") },
            confirmButton = {
                Button(onClick = {
                    try {
                        deleteGaleriaViewModel.deletar(idImgIndex){onResult ->
                            if (onResult){
                                showConfirmEditGaleria = false
                                showModalSucesso = true
                                navController.navigate("PerfilPrestadorScreen")
                            }else{
                                showConfirmEditGaleria = false
                                showModalErro = true
                            }
                        }
                    }catch(e:Exception){
                        showConfirmEditGaleria = false
                        showModalErro = true
                    }

                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showConfirmEditGaleria = false }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (showAddGaleria) {
        fun uriToFile(uri: Uri, context: Context): File {
            val inputStream = context.contentResolver.openInputStream(uri)
            val tempFile = File.createTempFile("temp_image", null, context.cacheDir)
            val outputStream = FileOutputStream(tempFile)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            return tempFile
        }

        val context = LocalContext.current
        var fileAnexada by remember { mutableStateOf<File?>(null) }
        Log.d("TESTE123", "${fileAnexada}")
        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                fileAnexada = uriToFile(uri, context)
            }
        }

        AlertDialog(
            onDismissRequest = { showAddGaleria = false },
            title = {
                Text(
                    text = "Adicionar foto de perfil",
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
            },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape)
                            .background(color = Color(0xFFCDD3E0))
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredWidth(width = 33.dp)
                                .requiredHeight(height = 32.dp)
                                .clip(shape = CircleShape)
                                .clickable {
                                    galleryLauncher.launch("image/*")
                                }
                                .background(color = Color(0xff588aed))
                        ) {
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
                    onClick = {
                        try {
                            if (fileAnexada != null) {
                                anexarGaleriaViewModel.anexar(fileAnexada!!) { onResult ->
                                    if (onResult) {
                                        Log.d("IMGGALERIA", "SUCESSO")
                                        showModalSucesso = true
                                        showAddGaleria = false
                                        navController.navigate("PerfilPrestadorScreen")
                                    }else{
                                        Log.d("IMGGALERIA", "FALHA")
                                        showModalErro= true
                                        showAddGaleria = false
                                    }
                                }
                            } else {
                                Log.d("IMGGALERIA", "Não anexou uma imagem")
                                showModalErro = true
                                showAddGaleria = false
                            }
                        } catch (e: Exception) {
                            Log.d("IMGGALERIA", "Exception::$e")
                            showAddGaleria = false
                        }
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