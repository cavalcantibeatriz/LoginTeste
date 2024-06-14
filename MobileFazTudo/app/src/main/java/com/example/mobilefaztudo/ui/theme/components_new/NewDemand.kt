package com.example.mobilefaztudo.ui.theme.components_new
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Frame17(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Selecione a Categoria") }
    val items = listOf("limpeza", "Obras", "Hidraúlica","Elétrica","Mecânica","Todos")
    //NÃO APAGARRR
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        // Aqui para lidar com a URI da imagem selecionada
    }
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .requiredWidth(width = 300.dp)
            .requiredHeight(height = 300.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top
    ) {
        //TITLE
        Text(
            text = "Nova Demanda",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        //INPUT
        OutlinedTextField(
            value = descricao,
            onValueChange = { newText -> descricao = newText },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            label = { Text("Descreva sua necessidade") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(15.dp))
                .background(color = Color(0xffff5c00).copy(alpha = 0.2f))
        )
        Spacer(modifier = Modifier.height(10.dp))
        
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(color = Color(0xFFCDD3E0))
        ){
            Box(
                modifier = Modifier
                    .requiredWidth(width = 33.dp)
                    .requiredHeight(height = 32.dp)
                    .clip(shape = CircleShape)
                    .clickable { /* TODO: Bia não é um botão mas é clicavel, mim perdoe */ }
                    .background(color = Color(0xff588aed))
            ){
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Arrow - Down 2",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )

            }
            Text(
                text = "Selecione uma categoria",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(color = Color(0xFFCDD3E0))
        ){
            Box(
                modifier = Modifier
                    .requiredWidth(width = 33.dp)
                    .requiredHeight(height = 32.dp)
                    .clip(shape = CircleShape)
                    .clickable {
                        galleryLauncher.launch("image/*")
                    }
                    .background(color = Color(0xff588aed))
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Arrow - Down 2",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }
            Text(
                text = "Adicione uma imagem",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )

        }
        Spacer(modifier = Modifier.height(7.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .border(3.dp, Color.Red),
            horizontalArrangement = Arrangement.SpaceAround
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
    }

    //Exibição do drop
//    Box(
//        modifier = Modifier
//            .wrapContentSize()
//            .clickable(onClick = { expanded = true })
//            .padding(16.dp)
//            .offset(x = 78.dp,
//                y = 122.dp)
//    ) {
//        Text(text = selectedItem)
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            items.forEach { item ->
//                DropdownMenuItem(onClick = {
//                    selectedItem = item
//                    expanded = false
//                }) {
//                    Text(text = item)
//                }
//            }
//        }
//    }
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

@Preview
@Composable
private fun Frame17Preview() {
    Column (
        modifier=Modifier
        .fillMaxSize()
        .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente

    ){
        Frame17(Modifier)

    }
}