package com.example.mobilefaztudo.view.TelasPerfil

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundPerfilContratante
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
@Composable
fun PerfilContratanteScreen (
    navController: NavController, sharedPreferencesHelper: SharedPreferencesHelper
){
    Box(
    modifier = Modifier
    .fillMaxSize()
    .background(Color.White)
    ) {
        BackgroundPerfilContratante()
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
                        text = "Tela do perfil CONTRATANTE",
                        fontSize = 30.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
            }
            NavBarContratante(sharedPreferencesHelper = sharedPreferencesHelper, navController = navController,initialState = "Person")
        }
    }
}

