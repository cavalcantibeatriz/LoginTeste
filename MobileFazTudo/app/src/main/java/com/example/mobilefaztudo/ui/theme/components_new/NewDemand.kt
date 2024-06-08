package com.example.mobilefaztudo.ui.theme.components_new
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilefaztudo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Frame17(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 292.dp)
            .requiredHeight(height = 280.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp,
                    y = 73.dp)
                .requiredWidth(width = 252.dp)
                .requiredHeight(height = 36.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 252.dp)
                    .requiredHeight(height = 36.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium)) {append("Descrição: \n")}
                        withStyle(style = SpanStyle(
                            color = Color.White,
                            fontSize = 16.sp)) {append("\n")}},
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth() .offset(x = 0.dp,
                y = 210.dp),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            ActionButton(
                text = "Cancelar",
                backgroundColor = Color(0xff992323),
                onClick = { /* TODO: Adicione a ação de cancelar */ }
            )
            ActionButton(
                text = "Continuar",
                backgroundColor = Color(0xff239960),
                onClick = { /* TODO: Adicione a ação de confirmar */ }
            )
        }

        Text(
            text = "Nova Demanda",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 60.dp,
                    y = 19.dp)
                .requiredWidth(width = 172.dp)
                .requiredHeight(height = 34.dp))

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 28.dp,
                    y = 169.dp)
                .requiredWidth(width = 33.dp)
                .requiredHeight(height = 32.dp)
                .clip(shape = CircleShape)
                .clickable { /* TODO: Bia não é um botão mas é clicavel, mim perdoe */  }
                .background(color = Color(0xff588aed)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 28.dp,
                    y = 132.dp)
                .requiredWidth(width = 33.dp)
                .requiredHeight(height = 32.dp)
                .clip(shape = CircleShape)
                .clickable { /* TODO: Bia não é um botão mas é clicavel, mim perdoe */  }
                .background(color = Color(0xff588aed)))
        Text(
            text = "Adicionar imagem",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 14.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 72.dp,
                    y = 175.dp)
                .requiredWidth(width = 174.dp)
                .requiredHeight(height = 20.dp))

        var expanded by remember { mutableStateOf(false) }
        var selectedItem by remember { mutableStateOf("Selecione a Categoria") }
        val items = listOf("limpeza", "Obras", "Hidraúlica","Elétrica","Mecânica","Todos")

        Box(
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = { expanded = true })
                .padding(16.dp)
                .offset(x = 78.dp,
                    y = 122.dp)
        ) {
            Text(text = selectedItem)

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedItem = item
                        expanded = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.combined_shape),
            contentDescription = "Arrow - Down 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 34.dp,
                    y = 175.dp)
                .requiredSize(size = 20.dp))
        var text = ""
            
        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            label = { Text("Label") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 110.dp, y = 73.dp)
                .requiredWidth(width = 148.dp)
                .requiredHeight(height = 28.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = Color(0xffff5c00).copy(alpha = 0.2f))
        )

        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Arrow - Down 2",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 35.dp,
                    y = 139.dp)
                .requiredSize(size = 19.dp))
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

@Composable
fun ActionButtona(text: String, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview(widthDp = 292, heightDp = 280)
@Composable
private fun Frame17Preview() {
    Frame17(Modifier)
}