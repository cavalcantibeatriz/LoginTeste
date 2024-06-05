package com.example.faztudo_mb.ui.theme.screens.components_new
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilefaztudo.R

@Composable
fun DemandInProgress(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .width(355.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            Text(
                text = "Preciso de pintura em um quarto inteiro.",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 128.dp, y = 45.dp)
                    .width(210.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Rectangle 70",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 21.dp, y = 17.dp)
                    .width(86.dp)
                    .height(93.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = "Pintura de quarto",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 128.dp, y = 16.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(bottomStart = 38.dp, bottomEnd = 38.dp))
                .background(Color(0xffff7a00))
        ) {
            Text(
                text = "Em Andamento",
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 23.dp)
            )
        }
    }
}


@Preview(widthDp = 355, heightDp = 128)
@Composable
private fun DemandInProgressPreview() {
    DemandInProgress(Modifier)
}
