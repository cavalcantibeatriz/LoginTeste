package com.example.mobilefaztudo.view

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
import androidx.compose.runtime.remember
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
import com.example.faztudo_mb.ui.theme.screens.components.BackgroundRegister
import com.example.faztudo_mb.ui.theme.screens.components.imagem
import com.example.faztudo_mb.ui.theme.screens.components_new.PrestadorCard
import com.example.faztudo_mb.ui.theme.screens.components_new.TopBar
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.components_new.NavBar.NavBarContratante
import com.example.mobilefaztudo.viewModel.ListPrestadoresViewModel
import java.io.ByteArrayInputStream

@Composable
fun encontrePrestadores(
    navController: NavController,
    viewModel: ListPrestadoresViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    sharedPreferencesHelper: SharedPreferencesHelper) {

    val listPrestadores by viewModel.listPrestadores.observeAsState(initial = emptyList())

    LaunchedEffect(Unit){
        viewModel.listarPrestadores()
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
                        text = "Encontre prestadores",
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
                        listPrestadores.forEach { prestador ->
                            PrestadorCard(navController = navController, prestador = prestador)
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                    }
                }
            }
NavBarContratante(sharedPreferencesHelper, navController,"Home")
        }
    }
}
fun Base64ToPainter(base64String :String) : Painter?{
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(decodedString))
        bitmap?.let {
            BitmapPainter(it.asImageBitmap())
        }
    }catch (e:Exception){
        e.printStackTrace()
        null
    }
}
@Composable
fun Base64Image(base64String :String, modifier: Modifier = Modifier){
    val imagePainter: Painter? = remember { Base64ToPainter(base64String) }

    imagePainter?.let {
        Image(
            modifier = modifier,
            painter = it,
            contentDescription = "Imagem em base64")
    }?:run{

    }
}
