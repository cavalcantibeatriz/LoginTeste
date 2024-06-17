package com.example.mobilefaztudo.viewModel.Ambos

import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IUpdateImgPerfilRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream

class AtualizarPerfilViewModel(
    private val repository: IUpdateImgPerfilRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    fun atualizarImgPerfil(idUser: Int, file: File, onResult: (Boolean) -> Unit) {
        val filePart = prepareFilePart(file)
        Log.d("ImgPerfil", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.atualizarImgPerfil(authToken, idUser, filePart)
                if (response.isSuccessful) {
                    onResult(true)
                    Log.d("ImgPerfil", "SUCESSO")
                    val base64Image = fileToBase64(file)
                    sharedPreferencesHelper.saveNovaImgPerfil(base64Image)
                } else {
                    onResult(false)
                    Log.d("ImgPerfil", "FALHA")
                }
            } catch (e: Exception) {
                Log.d("ImgPerfil", "EXCEPTION:::${e}")
                onResult(false)
            }
        }
    }

    fun prepareFilePart(file: File): MultipartBody.Part {
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }

    fun fileToBase64(file: File): String {
        val bytes = FileInputStream(file).use { it.readBytes() }
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}