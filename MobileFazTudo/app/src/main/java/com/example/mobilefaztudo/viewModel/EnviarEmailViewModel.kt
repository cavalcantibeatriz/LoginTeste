package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.formDataEmail
import com.example.mobilefaztudo.repository.IEnviarEmailRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class EnviarEmailViewModel(
    private val repository: IEnviarEmailRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel(){

    fun enviarEmail(
        to: String, subject: String, body: String, onResult: (Boolean) -> Unit){
        Log.d("EMAIL", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.enviarEmail(authToken,
                    formDataEmail(to,subject,body))
                if (response.isSuccessful){
                    onResult(true)
                    Log.d("EMAIL", "SUCESSO")
                }else{
                    onResult(false)
                    Log.d("EMAIL", "FALHA")
                }
            }catch (e:Exception){
                onResult(false)
                Log.d("EMAIL", "EXCEPTION ${e}")
            }
        }
    }
}