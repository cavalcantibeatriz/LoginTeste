package com.example.mobilefaztudo.viewModel.Ambos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.formSenha
import com.example.mobilefaztudo.repository.IUpdateImgPerfilRepository
import com.example.mobilefaztudo.repository.IUpdateSenhaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class AtualizarSenhaViewModel(
    private val repository: IUpdateSenhaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    fun atualizarSenha(senha: String, onResult: (Boolean) -> Unit) {
        Log.d("ATUALIZARSENHA", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                var idUser = sharedPreferencesHelper.getIdUser()
                val authToken = sharedPreferencesHelper.getAuthToken()
                var response = repository.atualizarSenha(authToken, idUser, formSenha(senha))
                if (response.isSuccessful) {
                    Log.d("ATUALIZARSENHA", "SUCESSO")
                    onResult(true)
                } else {
                    Log.d("ATUALIZARSENHA", "FALHA")
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
                Log.d("ATUALIZARSENHA", "Exception:::$e")
            }
        }
    }
}