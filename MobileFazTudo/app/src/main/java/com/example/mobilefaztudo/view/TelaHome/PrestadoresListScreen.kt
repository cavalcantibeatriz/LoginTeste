package com.example.mobilefaztudo.view.TelaHome

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPrestador
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandCard
import com.example.faztudo_mb.ui.theme.screens.components_new.PrestadorCard
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.IconBox
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.view.TelaAcompanhamento.FilterButton
import com.example.mobilefaztudo.view.TelaAcompanhamento.FilterTipo
import com.example.mobilefaztudo.viewModel.Contratante.DesfavoritarViewModel
import com.example.mobilefaztudo.viewModel.Contratante.FavoritarViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListFavoriteViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListPrestadoresViewModel
import kotlinx.coroutines.delay
import java.io.ByteArrayInputStream

@Composable
fun encontrePrestadores(
    navController: NavController,
    viewModel: ListPrestadoresViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper,
    favoritarViewModel: FavoritarViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    desfavoritarViewModel: DesfavoritarViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    listPrestadoresFavoritos: ListFavoriteViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val listPrestadores by viewModel.listPrestadores.observeAsState(initial = emptyList())
    var exibirFiltro by remember { mutableStateOf<FilterTipo?>(null) }

    val prestadoresMecanica = listPrestadores.filter { prestador ->
        prestador.category?.id == 1
    }
    val prestadoresHidraulica = listPrestadores.filter { prestador ->
        prestador.category?.id == 2
    }
    val prestadoresLimpeza = listPrestadores.filter { prestador ->
        prestador.category?.id == 3
    }
    val prestadoresEletrica = listPrestadores.filter { prestador ->
        prestador.category?.id == 4
    }
    val prestadoresObras = listPrestadores.filter { prestador ->
        prestador.category?.id == 5
    }
    val prestadoresTodos = listPrestadores.filter { prestador ->
        prestador.category?.id == 6
    }


    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            viewModel.listarPrestadores()
        }
    }

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                TopBar(navController=navController,sharedPreferencesHelper= sharedPreferencesHelper)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Encontre prestadores",
                        fontSize = 30.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            FilterButton(
                                text = "Mecânica",
                                isSelected = exibirFiltro == FilterTipo.MECANICA,
                                onClick = {
                                    exibirFiltro =
                                        if (exibirFiltro == FilterTipo.MECANICA) null else FilterTipo.MECANICA
                                })
                            FilterButton(
                                text = "Obras",
                                isSelected = exibirFiltro == FilterTipo.OBRAS,
                                onClick = {
                                    exibirFiltro =
                                        if (exibirFiltro == FilterTipo.OBRAS) null else FilterTipo.OBRAS
                                })
                            FilterButton(
                                text = "Limpeza",
                                isSelected = exibirFiltro == FilterTipo.LIMPEZA,
                                onClick = {
                                    exibirFiltro =
                                        if (exibirFiltro == FilterTipo.LIMPEZA) null else FilterTipo.LIMPEZA
                                })
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            FilterButton(
                                text = "Elétrica",
                                isSelected = exibirFiltro == FilterTipo.ELETRICA,
                                onClick = {
                                    exibirFiltro =
                                        if (exibirFiltro == FilterTipo.ELETRICA) null else FilterTipo.ELETRICA
                                })
                            FilterButton(
                                text = "Hidráulica",
                                isSelected = exibirFiltro == FilterTipo.HIDRAULICA,
                                onClick = {
                                    exibirFiltro =
                                        if (exibirFiltro == FilterTipo.HIDRAULICA) null else FilterTipo.HIDRAULICA
                                })

                            FilterButton(
                                text = "Geral",
                                isSelected = exibirFiltro == FilterTipo.TODOS,
                                onClick = {
                                    exibirFiltro =
                                        if (exibirFiltro == FilterTipo.TODOS) null else FilterTipo.TODOS
                                })

                        }
                        Spacer(modifier = Modifier.padding(3.dp))

                        if (listPrestadores.isEmpty()){
                            Text(text = "Não identificamos prestadores no momento :(")
                        }else {
                            when(exibirFiltro){
                                FilterTipo.ELETRICA -> {
                                    if (prestadoresEletrica.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos prestadores na categoria :(")
                                    } else {
                                        prestadoresEletrica.forEach { prestador ->
                                            PrestadorCard(
                                                navController = navController,
                                                prestador = prestador,
                                                favoritarViewModel = favoritarViewModel,
                                                desfavoritarViewModel = desfavoritarViewModel,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                listPrestadoresFavoritos = listPrestadoresFavoritos,
                                                listPrestadores = viewModel
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                FilterTipo.LIMPEZA -> {
                                    if (prestadoresLimpeza.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos prestadores na categoria :(")
                                    } else {
                                        prestadoresLimpeza.forEach { prestador ->
                                            PrestadorCard(
                                                navController = navController,
                                                prestador = prestador,
                                                favoritarViewModel = favoritarViewModel,
                                                desfavoritarViewModel = desfavoritarViewModel,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                listPrestadoresFavoritos = listPrestadoresFavoritos,
                                                listPrestadores = viewModel
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }

                                FilterTipo.OBRAS -> {
                                    if (prestadoresObras.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos prestadores na categoria :(")
                                    } else {
                                        prestadoresObras.forEach { prestador ->
                                            PrestadorCard(
                                                navController = navController,
                                                prestador = prestador,
                                                favoritarViewModel = favoritarViewModel,
                                                desfavoritarViewModel = desfavoritarViewModel,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                listPrestadoresFavoritos = listPrestadoresFavoritos,
                                                listPrestadores = viewModel
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }

                                FilterTipo.HIDRAULICA -> {
                                    if (prestadoresHidraulica.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos prestadores na categoria :(")
                                    } else {
                                        prestadoresHidraulica.forEach { prestador ->
                                            PrestadorCard(
                                                navController = navController,
                                                prestador = prestador,
                                                favoritarViewModel = favoritarViewModel,
                                                desfavoritarViewModel = desfavoritarViewModel,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                listPrestadoresFavoritos = listPrestadoresFavoritos,
                                                listPrestadores = viewModel
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }

                                FilterTipo.TODOS -> {
                                    if (prestadoresTodos.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos prestadores na categoria :(")
                                    } else {
                                        prestadoresTodos.forEach { prestador ->
                                            PrestadorCard(
                                                navController = navController,
                                                prestador = prestador,
                                                favoritarViewModel = favoritarViewModel,
                                                desfavoritarViewModel = desfavoritarViewModel,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                listPrestadoresFavoritos = listPrestadoresFavoritos,
                                                listPrestadores = viewModel
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }

                                FilterTipo.MECANICA -> {
                                    if (prestadoresMecanica.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos prestadores na categoria :(")
                                    } else {
                                        prestadoresMecanica.forEach { prestador ->
                                            PrestadorCard(
                                                navController = navController,
                                                prestador = prestador,
                                                favoritarViewModel = favoritarViewModel,
                                                desfavoritarViewModel = desfavoritarViewModel,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                listPrestadoresFavoritos = listPrestadoresFavoritos,
                                                listPrestadores = viewModel
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }

                                null -> {
                                    listPrestadores.forEach { prestador ->
                                        PrestadorCard(
                                            navController = navController,
                                            prestador = prestador,
                                            favoritarViewModel = favoritarViewModel,
                                            desfavoritarViewModel = desfavoritarViewModel,
                                            sharedPreferencesHelper = sharedPreferencesHelper,
                                            listPrestadoresFavoritos = listPrestadoresFavoritos,
                                            listPrestadores = viewModel
                                        )
                                        Spacer(modifier = Modifier.padding(10.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            NavBarContratante(sharedPreferencesHelper, navController, "Home")
        }
    }
}

fun Base64ToPainter(base64String: String): Painter? {
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(decodedString))
        bitmap?.let {
            BitmapPainter(it.asImageBitmap())
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
@Composable
fun Base64Image(base64String: String, modifier: Modifier = Modifier) {
    val imagePainter: Painter? = remember { Base64ToPainter(base64String) }
    imagePainter?.let {
        Image(
            modifier = modifier,
            painter = it,
            contentDescription = "Imagem em base64"
        )
    } ?: run {
    }
}
