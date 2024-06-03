package com.example.mobilefaztudo.ui.theme.components_new

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavBarFuncional() {
    val selectedIcon = remember { mutableStateOf(Icons.Filled.Home)}
    val personBoxModifier = remember { mutableStateOf(Modifier.size(70.dp)) }
    val infoBoxModifier = remember { mutableStateOf(Modifier.size(70.dp)) }
    val homeBoxModifier = remember { mutableStateOf(Modifier.size(70.dp).clip(CircleShape).background(color = Color(0xFFF7A000)))  }
    val notificationsBoxModifier = remember { mutableStateOf(Modifier.size(70.dp)) }
    val favoriteBoxModifier = remember { mutableStateOf(Modifier.size(70.dp)) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(65.dp).align(Alignment.BottomCenter)
//                .clip(shape = RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                .background(color = Color.White),
        )
        Row(
            modifier = Modifier.
            fillMaxWidth()
                .height(65.dp)
                .align(Alignment.BottomCenter) ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = personBoxModifier.value
                .background(if (selectedIcon.value == Icons.Filled.Person) Color(0xFFF7A000) else Color.White)
                .clickable {
                    selectedIcon.value = Icons.Filled.Person
                    personBoxModifier.value = Modifier.size(70.dp).clip(CircleShape)
                    infoBoxModifier.value = Modifier.size(70.dp)
                    homeBoxModifier.value = Modifier.size(70.dp)
                    favoriteBoxModifier.value = Modifier.size(70.dp)
                    notificationsBoxModifier.value = Modifier.size(70.dp)
                }
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
                modifier = infoBoxModifier.value
                    .background(if (selectedIcon.value == Icons.Filled.Info) Color(0xFFF7A000) else Color.White)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Info
                        personBoxModifier.value = Modifier.size(70.dp)
                        infoBoxModifier.value = Modifier.size(70.dp).clip(CircleShape)
                        homeBoxModifier.value = Modifier.size(70.dp)
                        favoriteBoxModifier.value = Modifier.size(70.dp)
                        notificationsBoxModifier.value = Modifier.size(70.dp)
                    }
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
                modifier = homeBoxModifier.value
                    .background(if (selectedIcon.value == Icons.Filled.Home) Color(0xFFF7A000) else Color.White)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Home
                        personBoxModifier.value = Modifier.size(70.dp)
                        infoBoxModifier.value = Modifier.size(70.dp)
                        homeBoxModifier.value = Modifier.size(70.dp).clip(CircleShape)
                        favoriteBoxModifier.value = Modifier.size(70.dp)
                        notificationsBoxModifier.value = Modifier.size(70.dp)
                    }
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
                modifier = favoriteBoxModifier.value
                    .background(if (selectedIcon.value == Icons.Filled.Favorite) Color(0xFFF7A000) else Color.White)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Favorite
                        personBoxModifier.value = Modifier.size(70.dp)
                        infoBoxModifier.value = Modifier.size(70.dp)
                        homeBoxModifier.value = Modifier.size(70.dp)
                        favoriteBoxModifier.value = Modifier.size(70.dp).clip(CircleShape)
                        notificationsBoxModifier.value = Modifier.size(70.dp)
                    }
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
                modifier = notificationsBoxModifier.value
                    .background(if (selectedIcon.value == Icons.Filled.Notifications) Color(0xFFF7A000) else Color.White)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Notifications
                        personBoxModifier.value = Modifier.size(70.dp)
                        infoBoxModifier.value = Modifier.size(70.dp)
                        homeBoxModifier.value = Modifier.size(70.dp)
                        favoriteBoxModifier.value = Modifier.size(70.dp)
                        notificationsBoxModifier.value = Modifier.size(70.dp).clip(CircleShape)
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "bell-nav 1",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}