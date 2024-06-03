package com.example.faztudo_mb.ui.theme.screens.components_new

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
@Preview
@Composable
fun NavBarFavorites(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(65.dp).align(Alignment.BottomCenter)
                .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(color = Color(0xff588aed)),
        )
        Row(
            modifier = Modifier.
            fillMaxWidth()
                .height(65.dp)
                .align(Alignment.BottomCenter) ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "image 56",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(70.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "icons8-aperto-de-mo-50 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(70.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "perfil-icon 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xFFF7A000))
            ) {
                Image(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "icons8-aperto-de-mo-50 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = Modifier
                    .size(70.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "bell-nav 1",
                    modifier = Modifier.size(width = 45.dp, height = 50.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}
