package com.example.mobilefaztudo.view

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPrestador
import com.example.faztudo_mb.ui.theme.screens.components_new.PrestadorCard
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.viewModel.Contratante.DesfavoritarViewModel
import com.example.mobilefaztudo.viewModel.Contratante.FavoritarViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListFavoriteViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListPrestadoresViewModel
import com.example.mobilefaztudo.viewModel.GetGaleriaViewModel
import kotlinx.coroutines.delay

@Composable
fun FavoritosScreen(
    navController: NavController,
    viewModel: ListFavoriteViewModel = viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper,
    favoritarViewModel: FavoritarViewModel = viewModel(),
    desfavoritarViewModel: DesfavoritarViewModel = viewModel(),
    listPrestadoresFavoritos: ListFavoriteViewModel = viewModel(),
    listPrestadores: ListPrestadoresViewModel = viewModel(),
    getGaleriaViewModel: GetGaleriaViewModel = viewModel()
) {
    val listPrestadoresFavoritados by viewModel.listPrestadoresFavorite.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        while (true){
            delay(1000)
            viewModel.listarPrestadoresFavoritos()
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
                        text = "Seus favoritos",
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
                        if (listPrestadoresFavoritados.isEmpty()){
                            Text(text = "Você não tem prestadores favoritados ...")
                        }else {
                            listPrestadoresFavoritados.forEach { prestador ->
                                PrestadorCard(
                                    navController = navController,
                                    prestador = prestador,
                                    favoritarViewModel = favoritarViewModel,
                                    desfavoritarViewModel = desfavoritarViewModel,
                                    sharedPreferencesHelper = sharedPreferencesHelper,
                                    listPrestadoresFavoritos = listPrestadoresFavoritos,
                                    listPrestadores = listPrestadores,
                                    getGaleriaViewModel = getGaleriaViewModel
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                            }
                        }
                    }
                }
            }
            NavBarContratante(
                sharedPreferencesHelper = sharedPreferencesHelper,
                navController = navController,
                initialState = "Favorite"
            )
        }
    }
}