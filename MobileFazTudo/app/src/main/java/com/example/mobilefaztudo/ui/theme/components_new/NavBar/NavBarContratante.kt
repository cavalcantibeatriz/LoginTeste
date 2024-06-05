package com.example.mobilefaztudo.ui.theme.components_new.NavBar

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper

@Composable
fun NavBarContratante(sharedPreferencesHelper: SharedPreferencesHelper,
                      navController: NavController,
                      initialState: String
){
    val selectedBoxModifier = remember { mutableStateOf(Modifier.size(70.dp).clip(CircleShape).background(color = Color(0xFFF7A000))) }

    fun stringToIcon(initialState: String): ImageVector {
        return when (initialState) {
            "Home" -> Icons.Filled.Home
            "Person" -> Icons.Filled.Person
            "Info" -> Icons.Filled.Info
            "Favorite" -> Icons.Filled.Favorite
            "Notifications" -> Icons.Filled.Notifications
            else -> Icons.Filled.Home // Define a imagem padrão caso a string não seja mapeada
        }
    }
    val initialIcon = stringToIcon(initialState)
    val selectedIcon = remember { mutableStateOf(initialIcon) }
    fun iconBackground(isSelected: Boolean): Modifier {
        return if (isSelected) selectedBoxModifier.value else Modifier.size(70.dp)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(65.dp).align(Alignment.BottomCenter)
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
                modifier = iconBackground(selectedIcon.value == Icons.Filled.Person)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Person
                        navController.navigate("PerfilContratanteScreen")
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "PROFILE",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = iconBackground(selectedIcon.value == Icons.Filled.Info)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Info
                        navController.navigate("Demand")
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
                modifier = iconBackground(selectedIcon.value == Icons.Filled.Home)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Home
                        navController.navigate("encontrePrestadores")
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "HOME",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = iconBackground(selectedIcon.value == Icons.Filled.Favorite)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Favorite
                        navController.navigate("encontreFavoritos")
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "FAVORITE",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
            Box(
                modifier = iconBackground(selectedIcon.value == Icons.Filled.Notifications)
                    .clickable {
                        selectedIcon.value = Icons.Filled.Notifications
                        navController.navigate("NotificacoesScreen")
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "NOTIFICAÇÃO",
                    modifier = Modifier.size(width = 45.dp, height = 45.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}
