package com.example.faztudo_mb.ui.theme.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilefaztudo.R

@Composable
fun RegisterLogoHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Logo()

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Text(
            text = "Crie sua conta",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,

        )

    }


}

@Composable
fun Logo(){
    Box(
        modifier = Modifier.size(58.dp),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logofaztudo___copia_1),
            contentDescription = "LogoRegisterHeader")
    }
}

@Preview
@Composable
fun RegisterLogoHeaderPreview(){
    RegisterLogoHeader()
}


