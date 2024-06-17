package com.example.mobilefaztudo.view.TelaAcompanhamento

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPrestador
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandFinished
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandInProgress
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandOpened
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.UploadImage
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.viewModel.AtrelarImagemDemandaViewModel
import com.example.mobilefaztudo.viewModel.PostarDemandaViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasUserViewModel
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DemandContratante(
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper,
    viewModel: ListDemandasUserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    postarDemandaViewModel: PostarDemandaViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    atrelarImagemDemandaViewModel: AtrelarImagemDemandaViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

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

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            fileAnexada = uriToFile(uri, context)
        }
    }

    val listDemandas by viewModel.listDemandasUser.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewModel.listarDemandasUser()
    }

    val demandasEmAndamento = listDemandas.filter { demanda -> demanda.status == "Negócio fechado!" && demanda.dataDeConclusao == null }
    val demandasConcluidas = listDemandas.filter { demanda -> demanda.status == "Negócio fechado!" && demanda.dataDeConclusao != null }
    val demandasEmAberto = listDemandas.filter { demanda -> demanda.status == "Interesse enviado por algum prestador de serviço" || demanda.status == "Recem criado" }
    var exibirCriacaoDemanda by remember { mutableStateOf(false) }
    var exibirFiltro by remember { mutableStateOf<FilterDemanda?>(null) }
    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundPrestador()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopBar(navController = navController, sharedPreferencesHelper = sharedPreferencesHelper)
            Spacer(modifier = Modifier.height(16.dp)) // Adiciona espaço entre a TopBar e o texto
            // Linha para ícone de filtro e texto
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            ) {
                Text(
                    text = "Demandas",
                    fontSize = 30.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(start = 20.dp) // Espaçamento mínimo entre texto e ícone
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = { exibirCriacaoDemanda = true },
                    modifier = Modifier.size(30.dp) // Tamanho maior para o ícone
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add), // Substitua com o ID do seu recurso de imagem
                        contentDescription = "Filtro",
                        modifier = Modifier.size(36.dp) // Tamanho maior para o ícone
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .weight(1f) // Utilize o restante do espaço disponível
                    .padding(3.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        FilterButton(
                            text = "Abertas",
                            isSelected = exibirFiltro == FilterDemanda.ABERTA,
                            onClick = {
                            exibirFiltro = if (exibirFiltro == FilterDemanda.ABERTA) null else FilterDemanda.ABERTA
                        })

                        FilterButton(
                            text = "Concluidas",
                            isSelected = exibirFiltro == FilterDemanda.CONCLUIDA,
                            onClick = {
                                exibirFiltro = if (exibirFiltro == FilterDemanda.CONCLUIDA) null else FilterDemanda.CONCLUIDA
                            })

                        FilterButton(
                            text = "Andamento",
                            isSelected = exibirFiltro == FilterDemanda.ANDAMENTO,
                            onClick = {
                                exibirFiltro = if (exibirFiltro == FilterDemanda.ANDAMENTO) null else FilterDemanda.ANDAMENTO
                            })

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    if (demandasEmAndamento.isEmpty() && demandasEmAberto.isEmpty() && demandasConcluidas.isEmpty()){
                        Text(text = "Você não tem demandas cadastradas ...")
                    }else{
                        when (exibirFiltro) {
                            FilterDemanda.ANDAMENTO -> {
                                demandasEmAndamento.forEach { demanda ->
                                    DemandInProgress(demanda)
                                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                                }
                            }
                            FilterDemanda.CONCLUIDA -> {
                                demandasConcluidas.forEach { demanda ->
                                    DemandFinished(demanda)
                                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                                }
                            }
                            FilterDemanda.ABERTA -> {
                                demandasEmAberto.forEach { demanda ->
                                    DemandOpened(demanda)
                                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                                }
                            }
                            null -> {
                                demandasEmAndamento.forEach { demanda ->
                                    DemandInProgress(demanda)
                                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                                }
                                demandasEmAberto.forEach { demanda ->
                                    DemandOpened(demanda)
                                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                                }
                                demandasConcluidas.forEach { demanda ->
                                    DemandFinished(demanda)
                                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                                }
                            }
                        }
                    }
                }
            }
            NavBarContratante(
                sharedPreferencesHelper = sharedPreferencesHelper,
                navController = navController,
                initialState = "Info"
            )
        }
    }

    if (exibirCriacaoDemanda) {
        var selectedOption by remember { mutableStateOf("Selecione uma categoria") }
        val options = listOf("Mecânica","Hidráulica","Limpeza", "Elétrica","Obras", "Todos")
        var isDropdownExpanded by remember { mutableStateOf(false) }
        var id by remember { mutableStateOf(0) }
        var fkContractor by remember { mutableStateOf(0) }
        var fkProvider by remember { mutableStateOf(0) }
        var descricao by remember { mutableStateOf("") }
        var avaliacao by remember { mutableStateOf("") }
        var status by remember { mutableStateOf("") }
        var categoria by remember { mutableStateOf(0) }
        var rating by remember { mutableStateOf(0) }
        var dataCriacao by remember { mutableStateOf("") }
        var dataDeConclusao by remember { mutableStateOf("") }
        var data by remember { mutableStateOf("") }

        Log.d("AAAAAA", "${descricao}")
        Log.d("AAAAAA", "${categoria}")
        Log.d("AAAAAA", "${fileAnexada}")

        fun categoriaTransform(nome: String): Int {
            var categoriaSelecionada = 0
            if (nome == "Mecânica") { categoriaSelecionada = 1 }
            if (nome == "Hidráulica") {categoriaSelecionada = 2 }
            if (nome == "Limpeza") { categoriaSelecionada = 3 }
            if (nome == "Elétrica") { categoriaSelecionada = 4 }
            if (nome == "Obras") { categoriaSelecionada = 5 }
            if (nome == "Todos") { categoriaSelecionada = 6 }
            return categoriaSelecionada
        }

        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        dataCriacao = currentDateTime.format(formatter)

        AlertDialog(
            onDismissRequest = { exibirCriacaoDemanda = false},
            title = { Text(
                    text = "Criar nova demanda",
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)) },
            text = { Column {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = descricao,
                        onValueChange = {it -> descricao = it},
                        label = { Text("Descreva sua necessidade") })
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        DropdownMenu(modifier = Modifier.width(270.dp), expanded = isDropdownExpanded, onDismissRequest = { isDropdownExpanded = false }){
                            options.forEach { option ->
                                DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                                        selectedOption = option
                                        isDropdownExpanded = false
                                        categoria = categoriaTransform(option)
                                    }, text = { Text(option) }) } }
                        Row(modifier = Modifier.fillMaxWidth().clip(CircleShape).background(color = Color(0xFFCDD3E0))){
                            Box(modifier = Modifier.requiredWidth(width = 33.dp).requiredHeight(height = 32.dp).clip(shape = CircleShape).clickable {
                                        isDropdownExpanded = !isDropdownExpanded }.background(color = Color(0xff588aed))) {
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow", tint = Color.Black, modifier = Modifier.align(alignment = Alignment.Center).clickable {
                                            isDropdownExpanded = !isDropdownExpanded // Alternar entre expandido e não expandido
                                 }) }
                            Text(text = selectedOption, color = Color.Black, textAlign = TextAlign.Center, style = MaterialTheme.typography.titleMedium, modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)) } }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (modifier = Modifier.fillMaxWidth().clip(CircleShape).background(color = Color(0xFFCDD3E0))){
                        Box(modifier = Modifier.requiredWidth(width = 33.dp).requiredHeight(height = 32.dp).clip(shape = CircleShape)
                            .clickable {
                                    galleryLauncher.launch("image/*")
                                }.background(color = Color(0xff588aed))){
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Arrow - Down 2", tint = Color.Black, modifier = Modifier.align(alignment = Alignment.Center)) }
                        Text(text = "Adicione uma imagem", color = Color.Black, textAlign = TextAlign.Center, style = MaterialTheme.typography.titleMedium, modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)) }
                    Spacer(modifier = Modifier.height(7.dp)) } },
            confirmButton = {
                Button(onClick = {
                        try{
                            val idContratante = sharedPreferencesHelper.getIdUser()
                            postarDemandaViewModel.postarDemanda(idContratante, id, fkContractor, fkProvider, descricao, avaliacao, status, categoria, rating, dataCriacao, dataDeConclusao, data) {onResult , idPost->
                                if (onResult){
                                    if (fileAnexada != null){
                                        atrelarImagemDemandaViewModel.atrelarImg(idPost,fileAnexada!!){ onResult ->
                                            if (onResult){
                                                Log.d("Demanda + Imagem", "sucesso")
                                            }else{
                                                Log.d("Demanda + Imagem", "falha")
                                            }
                                        }
                                    }
                                    Log.d("Demanda", "sucesso")
                                    showModalSucesso = true
                                    exibirCriacaoDemanda = false
                                }else{
                                    Log.d("Demanda", "falha")
                                    showModalErro = true
                                    exibirCriacaoDemanda = false
                                }
                            }
                        }catch (e:Exception){
                            Log.d("Demanda", "Exception:::${e}")
                        }
                    }
                ) { Text(text = "Confirmar") } },
            dismissButton = { Button(onClick = { exibirCriacaoDemanda = false }) { Text(text = "Cancelar") } }
        )
    }

    if (showModalSucesso){
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSucesso = false
            },
            title = { Text("Eba!") },
            text = { Text("Demanda criada com sucesso!") },
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
}
enum class FilterDemanda {
    ANDAMENTO, CONCLUIDA, ABERTA,
}

@Composable
fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.LightGray else Color.Gray,
            contentColor = if (isSelected) Color.Black else Color.White
        )
    ) {
        Text(text = text)
    }
}
