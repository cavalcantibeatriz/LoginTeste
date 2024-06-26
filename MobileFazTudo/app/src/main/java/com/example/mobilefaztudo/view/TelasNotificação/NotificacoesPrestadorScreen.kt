package com.example.mobilefaztudo.view.TelasNotificação

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
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
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundNotificacao
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.ContratanteNotificationCard
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarPrestador
import com.example.mobilefaztudo.ui.theme.components_new.PrestadorNotificationCard
import com.example.mobilefaztudo.viewModel.EnviarEmailViewModel
import com.example.mobilefaztudo.viewModel.NotificarInteresseViewModel
import kotlinx.coroutines.delay

@Composable
fun NotificacoesPrestadorScreen(
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper,
    listNotificarInteresseViewModel : NotificarInteresseViewModel = viewModel(),
    enviarEmailViewModel : EnviarEmailViewModel = viewModel()
){
    val listInteresses by listNotificarInteresseViewModel.listInteresses.observeAsState(initial = emptyList())
    val idUser = sharedPreferencesHelper.getIdUser()

    LaunchedEffect(Unit) {
        listNotificarInteresseViewModel.listNotificar()
    }
    val listPrestador = listInteresses.filter { it ->
        it.status == "Interesse aceito pelo contratante!" && it.prestador.id == idUser && it.post.dataDeConclusao == null
    }

    Log.d("VisualizarPrestador", " ${listPrestador}")


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BackgroundNotificacao()
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
                    Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification Icon",
                    modifier = Modifier.size(24.dp)
                )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Notificações",
                        fontSize = 30.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(3.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (listPrestador.isEmpty()){
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(text = "Não identificamos notificações para você :(")
                        }else{
                            listPrestador.forEach { it ->
                                ContratanteNotificationCard(it, enviarEmailViewModel, sharedPreferencesHelper)
                            }
                        }
                    }
                }
            }
            NavBarPrestador(sharedPreferencesHelper = sharedPreferencesHelper, navController = navController, initialState = "Notifications")
        }
    }
}
