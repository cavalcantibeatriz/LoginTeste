package com.example.faztudo_mb.ui.theme.screens.components_new

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilefaztudo.R



@Composable
fun PrestadorCard(
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(350.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )

            )
            .padding(top = 24.dp, bottom = 17.dp),
        contentAlignment = Alignment.Center

    ) {
        Row (modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ))){
            Image(modifier = modifier,
                painter = painterResource(id = R.drawable.rectangle_71),
                contentDescription = "Botao de Voltar"
            )
            Spacer(modifier = modifier.width(20.dp))
            Column (modifier = modifier){
                Text(
                    text = "Bruna Ferraz",
                    fontWeight = FontWeight.Bold,
                    //fontFamily = FontFamily(Font(R.font.poppins))
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "Especialista em Obras \n" +
                        "Gerais")
            Row (
                modifier = modifier
                    .padding(start = 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.image_63),
                    contentDescription = "Botao de Voltar")
                Spacer(modifier = modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.visualizar_1),
                    contentDescription = "Botao de Voltar"
                )

            }
            }
        }
    }
}

@Preview
@Composable
fun PrestadorCardPreview(){
    PrestadorCard()
}