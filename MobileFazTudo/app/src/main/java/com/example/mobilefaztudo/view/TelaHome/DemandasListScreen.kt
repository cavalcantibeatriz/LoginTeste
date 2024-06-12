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
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasViewModel

@Composable
fun encontreDemandas(
    navController: NavController,
    viewModel: ListDemandasViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper
) {
    val listDemandas by viewModel.listDemandas.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        viewModel.listarDemandas()
    }

    var exibirTela by remember { mutableStateOf(true) }
    var exibirFiltro by remember { mutableStateOf(false) }


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
                        IconButton(
                            onClick = { exibirFiltro = true },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icons8_filtro_24),
                                contentDescription = "Filtro",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
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
                            if (listDemandas.isEmpty()){
                                Text(text = "Sem demandas disponíveis :(")
                            }else {
                                listDemandas.forEach { demanda ->
                                    DemandCard(
                                        demanda = demanda,
                                        sharedPreferencesHelper = sharedPreferencesHelper
                                    )
                                    Spacer(modifier = Modifier.padding(10.dp))
                                }
                            }
                        }
                    }
                }
                NavBarPrestador(sharedPreferencesHelper, navController, "Home")
            }
        }

    if (exibirFiltro){
        Column (
            modifier=Modifier
                .fillMaxSize()
                .background(Color(color = 0x41000000)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
        ){
            IconBox()
        }
    }
}