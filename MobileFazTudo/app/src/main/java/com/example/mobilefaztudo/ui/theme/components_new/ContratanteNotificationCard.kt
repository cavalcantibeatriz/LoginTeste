package com.example.mobilefaztudo.ui.theme.components_new

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilefaztudo.R

@Composable
fun ContratanteNotificationCard(modifier: Modifier = Modifier) {
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
                .padding(top = 20.dp, bottom = 17.dp),
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
                    )
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(modifier = modifier,
                    painter = painterResource(id = R.drawable.rectangle_71__1_),
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
                    Text(
                        text = "Faço por R$300,00",
                        modifier = modifier
                            .width(180.dp)
                            .height(25.dp),
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Clip
                    )
                    Row (
                        modifier = modifier
                            .padding(start = 190.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_60),
                            contentDescription = "Botao de enviar dados",
                            modifier = Modifier
                                 // Ajuste o tamanho conforme necessário
                                .clickable {
                                    Direct()
                                })
                    }
                }
            }
        }
    Spacer(modifier = modifier.height(8.dp))

}

@Preview
@Composable
fun ContratanteNotificationCardPreview(){
    ContratanteNotificationCard()
}
fun Direct(){

}