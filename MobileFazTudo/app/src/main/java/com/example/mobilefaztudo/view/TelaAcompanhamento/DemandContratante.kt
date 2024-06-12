package com.example.mobilefaztudo.view.TelaAcompanhamento

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPrestador
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandFinished
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandInProgress
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandOpened
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.Frame17
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasUserViewModel

@Composable
fun DemandContratante(
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper,
    viewModel: ListDemandasUserViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val listDemandas by viewModel.listDemandasUser.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewModel.listarDemandasUser()
    }

    // Separar as demandas de acordo com o status
    val demandasEmAndamento = listDemandas.filter { demanda ->
        demanda.status == "Negócio fechado!" && demanda.dataDeConclusao == null
    }
    val demandasConcluidas = listDemandas.filter { demanda ->
        demanda.status == "Negócio fechado!" && demanda.dataDeConclusao != null
    }
    val demandasEmAberto = listDemandas.filter { demanda ->
        demanda.status == "Interesse enviado por algum prestador de serviço" || demanda.status == "Recem criado"
    }
    var exibirCriacaoDemanda by remember { mutableStateOf(false) }
    var exibirFiltro by remember { mutableStateOf<FilterDemanda?>(null) }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(color = 0x41000000)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
        ) {
            Frame17()
        }
    }
}
enum class FilterDemanda {
    ANDAMENTO, CONCLUIDA, ABERTA
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