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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundRegister
import com.example.faztudo_mb.ui.theme.screens.components.imagem
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandFinished
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandInProgress
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandOpened
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante

@Composable
fun Demand(
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundRegister(backgroundImageResId = imagem)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopBar()
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
                IconButton(
                    onClick = { /* Implementar a lógica do clique aqui */ },
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
                    DemandFinished()
                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                    DemandInProgress()
                    Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os cards
                    DemandOpened()
                }
            }
NavBarContratante(sharedPreferencesHelper = sharedPreferencesHelper, navController = navController, initialState = "Info")
        }
    }
}
