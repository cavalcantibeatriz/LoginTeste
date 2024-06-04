package com.example.mobilefaztudo.view.LoginECadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilefaztudo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen( navController: NavController) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        delay(2000) // Espera por 2 segundos
        navController.navigate("login")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff011741))
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Vector",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = (-55).dp,
                    y = 0.dp)
                .fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.logo_splash),
            contentDescription = "Rectangle 40",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 17.dp,
                    y = 300.dp)
                .requiredWidth(width = 359.dp)
                .requiredHeight(height = 39.dp))
        Text(
            text = "O Caminho Moderno \npara o Trabalho Autônomo",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 75.dp,
                    y = 650.dp))
    }
}
