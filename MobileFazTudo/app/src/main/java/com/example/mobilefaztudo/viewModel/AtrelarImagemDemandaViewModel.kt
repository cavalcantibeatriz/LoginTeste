package com.example.mobilefaztudo.viewModel

import android.media.Image
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.UploadImage
import com.example.mobilefaztudo.repository.IPostarDemandaRepository
import com.example.mobilefaztudo.repository.IUpdateImgDemandaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AtrelarImagemDemandaViewModel(
    private val repository: IUpdateImgDemandaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper) : ViewModel() {
    fun atrelarImg(idPost: Int, file: File, onResult: (Boolean)-> Unit) {
        val filePart = prepareFilePart(file)

        Log.d("Atrelar", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.atrelarDemanda(authToken, idPost, filePart)
                if (response.isSuccessful) {
                    Log.d("Atrelar", "SUCESSO")
                    onResult(true)
                } else {
                    Log.d("Atrelar", "FALHA")
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
                Log.d("Atrelar", "EXCEPTION:::${e}")
            }
        }
    }
    fun prepareFilePart(file: File): MultipartBody.Part {
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }
}