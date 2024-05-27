package com.example.faztudo_mb.ui.theme.screens.components_new

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilefaztudo.R


@Composable
fun NavBarHome(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(428.dp)
            .height(84.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(color = Color(0xff588aed))
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(84.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil_icon_1),
                    contentDescription = "image 56",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(84.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icons8_aperto_de_m_o_50_1),
                    contentDescription = "icons8-aperto-de-mo-50 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xFFF7A000))


            ) {
                Image(

                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "perfil-icon 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                    .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(84.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_56),
                    contentDescription = "icons8-aperto-de-mo-50 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }

            Box(
                modifier = Modifier
                    .size(84.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.bell_nav_1),
                    contentDescription = "bell-nav 1",
                    modifier = Modifier.size(width = 45.dp, height = 50.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Preview(widthDp = 428, heightDp = 84)
@Composable
private fun NavBarHomePreview() {
    NavBarHome()
}
