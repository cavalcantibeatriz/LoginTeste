package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.ILoginRepository
import com.example.mobilefaztudo.api.LoginRequestBody
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: ILoginRepository): ViewModel() {

     fun login(email: String, senha: String, onResult: (Boolean) -> Unit) {
        Log.d("LOGIN", "CHAMOU A VIEWMODEL")

        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequestBody(email, senha))
                if (response.isSuccessful) {
                    onResult(true)
                    Log.d("LOGIN", "sucesso")
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