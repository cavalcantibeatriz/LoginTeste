package com.example.mobilefaztudo.view.TelaHome

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundDemanda
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandCard
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.IconBox
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarPrestador
import com.example.mobilefaztudo.view.TelaAcompanhamento.FilterButton
import com.example.mobilefaztudo.view.TelaAcompanhamento.FilterDemanda
import com.example.mobilefaztudo.view.TelaAcompanhamento.FilterTipo
import com.example.mobilefaztudo.viewModel.EnviarMensagensViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasViewModel

@Composable
fun encontreDemandas(
    navController: NavController,
    viewModel: ListDemandasViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper,
    requestMensagem: EnviarMensagensViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val listDemandas by viewModel.listDemandas.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewModel.listarDemandas()
    }

    var exibirTela by remember { mutableStateOf(true) }
    var exibirFiltro by remember { mutableStateOf<FilterTipo?>(null) }

    val demandasMecanica = listDemandas.filter { demanda ->
        demanda.categoria == 1 && demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }
    val demandasHidraulica = listDemandas.filter { demanda ->
        demanda.categoria == 2 && demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }
    val demandasLimpeza = listDemandas.filter { demanda ->
        demanda.categoria == 3 && demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }
    val demandasEletrica = listDemandas.filter { demanda ->
        demanda.categoria == 4 && demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }
    val demandasObras = listDemandas.filter { demanda ->
        demanda.categoria == 5 && demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }
    val demandasTodos = listDemandas.filter { demanda ->
        demanda.categoria == 6 && demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }

    val demandasDefault = listDemandas.filter { demanda ->
        demanda.dataDeConclusao == null && demanda.fkProvider == 0
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundDemanda()
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
                    Text(
                        text = "Encontre demandas",
                        fontSize = 30.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
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

                        if (listDemandas.isEmpty()) {
                            Text(text = "Sem demandas disponíveis :(")
                        } else {
                            when (exibirFiltro) {
                                FilterTipo.ELETRICA -> {
                                    if (demandasEletrica.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos demandas na categoria :(")
                                    } else {
                                        demandasEletrica.forEach { demanda ->
                                            DemandCard(
                                                demanda = demanda,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                requestMensagem = requestMensagem
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                FilterTipo.LIMPEZA -> {
                                    if (demandasLimpeza.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos demandas na categoria :(")
                                    } else {
                                        demandasLimpeza.forEach { demanda ->
                                            DemandCard(
                                                demanda = demanda,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                requestMensagem = requestMensagem
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                FilterTipo.OBRAS -> {
                                    if (demandasObras.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos demandas na categoria :(")
                                    } else {
                                        demandasObras.forEach { demanda ->
                                            DemandCard(
                                                demanda = demanda,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                requestMensagem = requestMensagem
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                FilterTipo.HIDRAULICA -> {
                                    if (demandasHidraulica.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos demandas na categoria :(")
                                    } else {
                                        demandasHidraulica.forEach { demanda ->
                                            DemandCard(
                                                demanda = demanda,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                requestMensagem = requestMensagem
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                FilterTipo.TODOS -> {
                                    if (demandasTodos.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos demandas na categoria :(")
                                    } else {
                                        demandasTodos.forEach { demanda ->
                                            DemandCard(
                                                demanda = demanda,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                requestMensagem = requestMensagem
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                FilterTipo.MECANICA -> {
                                    if (demandasMecanica.isEmpty()) {
                                        Spacer(modifier = Modifier.padding(10.dp))
                                        Text(text = "Não identificamos demandas na categoria :(")
                                    } else {
                                        demandasMecanica.forEach { demanda ->
                                            DemandCard(
                                                demanda = demanda,
                                                sharedPreferencesHelper = sharedPreferencesHelper,
                                                requestMensagem = requestMensagem
                                            )
                                            Spacer(modifier = Modifier.padding(10.dp))
                                        }
                                    }
                                }
                                null -> {
                                    demandasDefault.forEach { demanda ->
                                        DemandCard(
                                            demanda = demanda,
                                            sharedPreferencesHelper = sharedPreferencesHelper,
                                            requestMensagem = requestMensagem
                                        )
                                        Spacer(modifier = Modifier.padding(10.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            NavBarPrestador(sharedPreferencesHelper, navController, "Home")
        }
    }
}
