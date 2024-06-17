package com.example.mobilefaztudo.viewModel.Prestador

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IAnexarGaleriaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AnexarGaleriaViewModel(
    private val repository: IAnexarGaleriaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    fun anexar(file: File, onResult: (Boolean) -> Unit) {
        Log.d("ANEXAR", "CHAMOU A VIEWMODEL")
        val filePart = prepareFilePart(file)

        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val idUser = sharedPreferencesHelper.getIdUser()
                val response = repository.anexarImagemGaleria(authToken, idUser, filePart)
                if (response.isSuccessful) {
                    Log.d("ANEXAR", "SUCESSO")
                    onResult(true)
                } else {
                    Log.d("ANEXAR", "FALHA")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.d("ANEXAR", "Exception:::$e")
                onResult(false)
            }
        }
    }

    fun prepareFilePart(file: File): MultipartBody.Part {
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }
}