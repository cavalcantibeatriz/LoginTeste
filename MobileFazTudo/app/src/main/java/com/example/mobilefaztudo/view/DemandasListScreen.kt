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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundRegister
import com.example.faztudo_mb.ui.theme.screens.components.imagem
import com.example.faztudo_mb.ui.theme.screens.components_new.DemandCard
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarPrestador
import com.example.mobilefaztudo.viewModel.ListDemandasViewModel

@Composable
fun encontreDemandas(
    navController: NavController,
    viewModel: ListDemandasViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper
    ) {
    val listDemandas by viewModel.listDemandas.observeAsState(initial = emptyList())
    LaunchedEffect(Unit){
        viewModel.listarDemandas()
    }
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Top
            ) {
                TopBar()
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
                        onClick = { /* Implementar a lógica do filtro aqui */ },
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
//                            .border(3.dp, Color.Red),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        listDemandas.forEach { demanda ->
                            DemandCard(demanda = demanda)
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                    }
                }
            }
            NavBarPrestador(sharedPreferencesHelper, navController, "Home" )
        }
    }
}
