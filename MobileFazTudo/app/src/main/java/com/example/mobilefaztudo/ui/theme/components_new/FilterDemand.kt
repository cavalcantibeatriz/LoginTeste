package com.example.mobilefaztudo.ui.theme.components_new

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilefaztudo.R

@Preview
@Composable
fun IconBox() {
    var selectedIcon by remember { mutableStateOf(-1) }
    var filtroApp by remember { mutableStateOf(6) }

    Box(
        modifier = Modifier
            .size(310.dp)
            .clip(RoundedCornerShape(16.dp)) // Adiciona bordas arredondadas
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonItem(
                    iconRes = R.drawable.icons8_vassoura_100_2,
                    text = "Limpeza",
                    onClick = {
                        selectedIcon = 0
                        filtroApp = 3},
                    isSelected = selectedIcon == 0
                )
                IconButtonItem(
                    iconRes = R.drawable.icons8_martelo_100_2,
                    text = "Obras",
                    onClick = { selectedIcon = 1
                        filtroApp = 5},
                    isSelected = selectedIcon == 1
                )
                IconButtonItem(
                    iconRes = R.drawable.image_63,
                    text = "Hidráulica",
                    onClick = { selectedIcon = 2
                        filtroApp = 2},
                    isSelected = selectedIcon == 2
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonItem(
                    iconRes = R.drawable.icons8_eletricidade_64_2,
                    text = "Elétrica",
                    onClick = { selectedIcon = 3
                        filtroApp = 4},
                    isSelected = selectedIcon == 3
                )
                IconButtonItem(
                    iconRes = R.drawable.icons8_ferramenta_100_2,
                    text = "Mecânica",
                    onClick = { selectedIcon = 4
                        filtroApp = 1},
                    isSelected = selectedIcon == 4
                )
                IconButtonItem(
                    iconRes = R.drawable.image_64,
                    text = "Todos",
                    onClick = { selectedIcon = 5
                        filtroApp = 6},
                    isSelected = selectedIcon == 5
                )
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre as linhas de ícones e os botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    text = "Cancelar",
                    backgroundColor = Color(0xff992323),
                    onClick = { /* TODO: Adicione a ação de cancelar */ }
                )
                ActionButton(
                    text = "Aplicar",
                    backgroundColor = Color(0xff239960),
                    onClick = { /* TODO: Adicione a ação de confirmar */ }
                )
            }
        }
    }
}

@Composable
fun IconButtonItem(
    iconRes: Int,
    text: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.size(80.dp),
            contentPadding = PaddingValues(0.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp, pressedElevation = 16.dp),
            border = if (isSelected) BorderStroke(2.dp, Color.Black) else null
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
        Text(text = text, color = Color.Black,  style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ActionButton(text: String, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = text, color = Color.White)
    }
}