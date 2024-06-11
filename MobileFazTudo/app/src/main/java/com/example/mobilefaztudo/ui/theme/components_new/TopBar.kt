package com.example.faztudo_mb.ui.theme.screens.components_new

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.AzulSite
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    sharedPreferencesHelper: SharedPreferencesHelper
) {
    var showModalSuccess by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                AzulSite,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )
            .padding(top = 32.dp, bottom = 15.dp),
        horizontalArrangement =
        Arrangement.Center,
        verticalAlignment =
        Alignment.CenterVertically

    ) {
        Row(
            modifier = modifier
                .padding(start = 25.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_71),
                contentDescription = "Botao de Voltar",
                Modifier.clickable { showModalSuccess = true }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = modifier
                .padding(end = 100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_67__2_),
                contentDescription = "LogoRegisterHeader"
            )
        }

        if (showModalSuccess) {
            Log.d("EXIBIR", "CHAMOU O MODAL")
            AlertDialog(
                onDismissRequest = {
                    showModalSuccess = false
                },
                title = { Text("Poxa...") },
                text = { Text("Deseja mesmo encerrar sua sessão?") },
                confirmButton = {
                    Button(onClick = {
                        sharedPreferencesHelper.clear()
                        navController.navigate("login")
                        showModalSuccess = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showModalSuccess = false
                    }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }

}

