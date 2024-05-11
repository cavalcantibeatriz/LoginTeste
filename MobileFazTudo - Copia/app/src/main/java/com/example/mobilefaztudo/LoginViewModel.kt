package com.example.mobilefaztudo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.Response
import com.example.mobilefaztudo.ApiService
import com.example.mobilefaztudo.RetrofitClient
import com.example.mobilefaztudo.LoginRequestBody
import com.example.mobilefaztudo.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val apiService: ApiService = RetrofitClient.apiService

    fun login(email: String, senha: String, onResult: (Boolean) -> Unit) {
        Log.d("LOGIN", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequestBody(email, senha))
                if (response.isSuccessful) {
                    onResult(true)
                    Log.d("LOGIN", "sucesso")
                } else {
                    onResult(true)
                    Log.d("LOGIN", "falha")
                }
            } catch (e: Exception) {
                Log.d("LOGIN", "exception ::: $e")
                onResult(true)

            }
        }
    }
}