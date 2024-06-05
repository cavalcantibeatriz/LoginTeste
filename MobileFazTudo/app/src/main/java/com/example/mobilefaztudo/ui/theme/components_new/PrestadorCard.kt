package com.example.faztudo_mb.ui.theme.screens.components_new

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.User
import java.io.ByteArrayInputStream


@Composable
fun PrestadorCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    prestador: User,
//    favoritarViewModel: FavoritarViewModel = viewModel(),
//    desfavoritarViewModel: DesfavoritarViewModel = viewModel()
) {
    val (currentImage, setCurrentImage) = remember { mutableStateOf(R.drawable.image_63) }
//    val idUser = sharedPreferencesHelper.getIdUser()

    var showModalError by remember { mutableStateOf(false) }
    var showModalSuccess by remember { mutableStateOf(false) }
    var showModalSuccessD by remember { mutableStateOf(false) }

    var showCardPrestador by remember { mutableStateOf(true) }
    var showPerfilPrestador by remember { mutableStateOf(false) }

    val lightGray = Color(0xFFD3D3D3)

    fun toggleImage() {
        if (currentImage == R.drawable.image_63) {
            setCurrentImage(R.drawable.image_72)
//            favoritarViewModel.postFavorite(idUser,prestador.id){onResult ->
                        showModalSuccess = true
        } else {
            setCurrentImage(R.drawable.image_63)
//            desfavoritarViewModel.deleteFavorite(idUser,prestador.id){onResult ->
                        showModalSuccessD = true
        }
    }

    if (showCardPrestador) {
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
                .padding(top = 17.dp, bottom = 17.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier
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
            ) {
                if (prestador.image_profile === null) {
                    Image(
                        modifier = modifier.width(100.dp),
                        painter = painterResource(R.drawable.img_profile_default),
                        contentDescription = "Botao de Voltar"
                    )
                } else {
                    prestador.image_profile?.let {
                        Base64Image(
                            base64String = it,
                            modifier = modifier.width(100.dp)
                        )
                    }
                }
                Spacer(modifier = modifier.width(20.dp))
                Column(modifier = modifier) {
                    Text(
                        text = prestador.name,
                        fontWeight = FontWeight.Bold,
                        //fontFamily = FontFamily(Font(R.font.poppins))
                        fontSize = 22.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Especialista em ${prestador.category?.name}",
                        modifier = modifier.width(180.dp),
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Clip
                    )
                    Row(
                        modifier = modifier
                            .padding(start = 140.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = currentImage),
                            contentDescription = "Imagem Alternável",
                            modifier = Modifier
                                .clickable { toggleImage() } // Adiciona a ação de clique
                        )
                        Spacer(modifier = modifier.width(10.dp))
                        Image(
                            modifier = Modifier.clickable {
                                if (!showPerfilPrestador) {
                                    showPerfilPrestador = true
                                }else{
                                    showPerfilPrestador = false
                                }},
                            painter = painterResource(id = R.drawable.visualizar_1),
                            contentDescription = "Botao de Voltar"
                        )
                    }
                }
            }
        }
    }

    if (showPerfilPrestador){
        Box(
            modifier = Modifier
                .height(420.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(lightGray)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { showPerfilPrestador = false }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Fechar",
                        tint = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                ) {
                    Text(
                        text = "Categoria - ${prestador.category?.name}",
                        fontSize = 25.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ){
                        Image(
                            painter = painterResource(R.drawable.img_profile_default),
                            contentDescription = "teste",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                        if (prestador.image_profile === null) {
                            Image(
                                painter = painterResource(R.drawable.img_profile_default),
                                contentDescription = "teste",
                                modifier = Modifier
                                    .size(130.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray)
                            )
                        } else {
                            prestador.image_profile?.let {
                                Base64Image(
                                    base64String = it,
                                    modifier = modifier
                                        .size(130.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(13.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "${prestador.name}${prestador.lastName}",
                        fontSize = 20.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "Descriçao do perfil aaaaaaa aaaaaa aaaa aaaaa aaaaaaa aaaaaa aaaaaaaaaaaa aaa aaaaaa",
                        fontSize = 20.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Clip,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Ver galeria")
                    }
                }
            }
        }
    }

    if (showModalSuccessD) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccessD = false
            },
            title = { Text("Ixi...") },
            text = { Text("Você desfavoritou o perfil de ${prestador.name} :(") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccessD = false
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showModalSuccess) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccess = false
            },
            title = { Text("Eba!") },
            text = { Text("Você adicionou ${prestador.name} como favorito!") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalSuccess = false
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showModalError) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalError = false
            },
            title = { Text("Oops") },
            text = { Text("Parece que algo deu errado :(") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalError = false
                }) {
                    Text("OK")
                }
            }
        )
    }
}

fun Base64ToPainter(base64String :String) : Painter?{
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(decodedString))
        bitmap?.let {
            BitmapPainter(it.asImageBitmap())
        }
    }catch (e:Exception){
        e.printStackTrace()
        null
    }
}
@Composable
fun Base64Image(base64String :String, modifier: Modifier = Modifier){
    val imagePainter: Painter? = remember { Base64ToPainter(base64String) }
    imagePainter?.let {
        Image(
            modifier = modifier,
            painter = it,
            contentDescription = "Imagem em base64")
    }?:run{}
}
