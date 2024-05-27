package com.example.mobilefaztudo.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.ILoginRepository
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.LoginResponse
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: ILoginRepository, private val sharedPreferencesHelper: SharedPreferencesHelper): ViewModel() {

     fun login(email: String, senha: String, onResult: (Boolean) -> Unit) {
        Log.d("LOGIN", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequestBody(email, senha))
                if (response.isSuccessful ) {
                    response.body()?.let { loginResponse ->
                        sharedPreferencesHelper.saveAuthToken(loginResponse.token)
                        sharedPreferencesHelper.saveUserData(loginResponse.login)
                    onResult(true)
                    Log.d("LOGIN", "sucesso")
                    Log.d("LOGIN", "${response.body() }")
                    } ?: run {
                        onResult(false)
                        Log.d("LOGIN", "Resposta de login vazia")
                    }
                } else {
                    onResult(false)
                    Log.d("LOGIN", "falha")
                }
            } catch (e: Exception) {
                //fazer validação por code
                Log.d("LOGIN", "exception ::: $e")
                onResult(false)
            }
        }
    }
}