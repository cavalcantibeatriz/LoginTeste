package com.example.faztudo_mb.ui.theme.screens.components_new

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import java.io.ByteArrayInputStream


@Composable
fun PrestadorCard(
    modifier: Modifier = Modifier,
    prestador: User,
//    favoritarViewModel: FavoritarViewModel = viewModel(),
//    desfavoritarViewModel: DesfavoritarViewModel = viewModel()
) {
    val (currentImage, setCurrentImage) = remember { mutableStateOf(R.drawable.image_63) }

//    val idUser = sharedPreferencesHelper.getIdUser()
//    val idUser = 12

    var showModalError by remember { mutableStateOf(false) }
    var showModalSuccess by remember { mutableStateOf(false) }
    var showModalSuccessD by remember { mutableStateOf(false) }

    fun toggleImage() {
        if (currentImage == R.drawable.image_63) {
            setCurrentImage(R.drawable.image_72)
//            favoritarViewModel.postFavorite(idUser,prestador.id){onResult ->
//                try {
//                    if (onResult) {
//                        Log.d("EXIBIR", "FAVORITAR OK")
//                        showModalSuccess = true
//                    } else {
//                        Log.d("EXIBIR", "FAVORITAR falha")
//                        showModalError = true
//                    }
//                }catch (e: Exception){
//                    Log.d("EXIBIR", "exception falha ${e}")
//                    showModalError = true
//                }
//            }
        } else {
            setCurrentImage(R.drawable.image_63)
//            desfavoritarViewModel.deleteFavorite(idUser,prestador.id){onResult ->
//                try {
//                    if (onResult) {
//                        Log.d("EXIBIR", "FAVORITAR OK")
//                        showModalSuccessD = true
//                    } else {
//                        Log.d("EXIBIR", "FAVORITAR falha")
//                        showModalError = true
//
//                    }
//                }catch (e:Exception){
//                    Log.d("EXIBIR", "exception falha ${e}")
//                    showModalError = true
//
//                }
//            }
        }
    }

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
            .padding(top = 24.dp, bottom = 17.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )),
            verticalAlignment = Alignment.CenterVertically){
            if (prestador.image_profile === null){
                Image(modifier = modifier.width(100.dp),
                    painter = painterResource(R.drawable.img_profile_default),
                    contentDescription = "Botao de Voltar"
                )
            }else {
                prestador.image_profile?.let { Base64Image(base64String = it, modifier = modifier.width(100.dp)) }
            }
            Spacer(modifier = modifier.width(20.dp))
            Column (modifier = modifier){
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
                    overflow = TextOverflow.Clip)
            Row (
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
                    painter = painterResource(id = R.drawable.visualizar_1),
                    contentDescription = "Botao de Voltar"
                )
            }
            }
        }
    }
    if (showModalSuccessD) {
        Log.d("EXIBIR", "CHAMOU O MODAL")
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSuccess = false
            },
            title = { Text("Ixi...") },
            text = { Text("Você desfavoritou o perfil de ${prestador.name}  :(") },
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
    }?:run{

    }
}
