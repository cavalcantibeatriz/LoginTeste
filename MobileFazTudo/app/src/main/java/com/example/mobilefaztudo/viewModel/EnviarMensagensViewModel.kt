package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.MensagemRequest
import com.example.mobilefaztudo.repository.IEnviarMensagemRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class EnviarMensagensViewModel (
    private val repository: IEnviarMensagemRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper)
    :ViewModel(){
    fun enviarMensagem(idDemanda: Int, body: MensagemRequest, onResult: (Boolean) -> Unit){
        Log.d("MENSAGEM", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try{
                val idUser = sharedPreferencesHelper.getIdUser()
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.enviarMensagem(authToken,idDemanda,idUser,body)
                if (response.isSuccessful){
                    onResult(true)
                    Log.d("MENSAGEM", "SUCESSO")
                }else{
                    onResult(false)
                    Log.d("MENSAGEM", "FALHA")
                }
            }catch (e : Exception){
                onResult(false)
                Log.d("MENSAGEM", "EXCEPTION AO ENVIAR ${e}")
            }
        }
        }
}