package com.example.mobilefaztudo.ui.theme.components_new

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.faztudo_mb.ui.theme.screens.components_new.Base64Image
import com.example.mobilefaztudo.R
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.viewModel.AtualizarPerfilViewModel
import java.io.File
import java.io.FileOutputStream

@Composable
fun PhotoProfile(
    navController: NavController,
    sharedPreferencesHelper : SharedPreferencesHelper,
    atualizarImgPerfilViewModel : AtualizarPerfilViewModel = viewModel(),
    tipoPerfil: String
) {
    fun uriToFile(uri: Uri, context: Context): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = File.createTempFile("temp_image", null, context.cacheDir)
        val outputStream = FileOutputStream(tempFile)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return tempFile
    }

    var imgProfileAtual = sharedPreferencesHelper.getImgProfile()
    var idUser = sharedPreferencesHelper.getIdUser()

    var showModalSucesso by remember { mutableStateOf(false) }
    var showModalErro by remember { mutableStateOf(false) }
    var showEditFoto by remember { mutableStateOf(false) }

    Box(modifier = Modifier.background(Color.Transparent)) {
        Box(
            modifier = Modifier
                .requiredSize(size = 198.dp)
                .clip(shape = CircleShape)
                .background(color = Color(0xffd9d9d9)),
            contentAlignment = Alignment.Center
        ) {
            if (imgProfileAtual == null || imgProfileAtual == ""){
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.rectangle_71__1_),
                    contentDescription = "Botão de Voltar",
                    contentScale = ContentScale.Crop
                )
            }else{
                Base64Image(
                    base64String = imgProfileAtual,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
        Image(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center)
                .clickable { showEditFoto = true },
            painter = painterResource(id = R.drawable.group),
            contentDescription = "Imagem Menor"
        )
    }
    if (showEditFoto){
        val context = LocalContext.current
        var fileAnexada by remember { mutableStateOf<File?>(null) }
        Log.d("TESTE123", "${fileAnexada}")

        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                fileAnexada = uriToFile(uri, context)
            }
        }

        AlertDialog(
            onDismissRequest = { showEditFoto = false},
            title = {
                Text(
                    text = "Edição foto de perfil",
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
            },
            text = {
                Column {
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
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        try {
                            if (fileAnexada != null) {
                                atualizarImgPerfilViewModel.atualizarImgPerfil(
                                    idUser,
                                    fileAnexada!!
                                ) { onReslt ->
                                    if (onReslt) {
                                        Log.d("IMGPROFILE", "sucesso")
                                        showModalSucesso = true
                                        showEditFoto = false
                                    } else {
                                        Log.d("IMGPROFILE", "falha")
                                        showModalErro = true
                                        showEditFoto = false
                                    }
                                }
                            }else{
                                Log.d("IMGPROFILE", "Não anexou uma imagem")
                                showModalErro = true
                                showEditFoto = false
                            }
                        }catch (e:Exception){
                            Log.d("IMGPROFILE", "Exception::$e")
                        }
                    }
                ) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showEditFoto = false }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }

    if (showModalSucesso){
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalSucesso = false
            },
            title = { Text("Eba!") },
            text = { Text("Imagem atualizada com sucesso!") },
            confirmButton = {
                Button(onClick = {
                    showModalSucesso = false
                    if (tipoPerfil == "P"){
                            navController.navigate("PerfilPrestadorScreen")
                    }else if(tipoPerfil == "C"){
                        navController.navigate("PerfilContratanteScreen")
                    }
                }) {
                    Text("OK")
                }
            }
        )
    }

    if (showModalErro){
        AlertDialog(
            onDismissRequest = {
                // Fechar o modal ao clicar fora
                showModalErro = false
            },
            title = { Text("Oops...") },
            text = { Text("Parece que alguma coisa deu errado...") },
            confirmButton = {
                Button(onClick = {
                    // Fechar o modal ao clicar no botão OK
                    showModalErro = false
                }) {
                    Text("OK")
                }
            }
        )
    }



}

