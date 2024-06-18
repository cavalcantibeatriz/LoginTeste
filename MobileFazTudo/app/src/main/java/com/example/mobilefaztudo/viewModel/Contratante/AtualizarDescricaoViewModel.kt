package com.example.mobilefaztudo.viewModel.Contratante

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.formDescricao
import com.example.mobilefaztudo.repository.IDeleteFavoriteRepository
import com.example.mobilefaztudo.repository.IDescricaoRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class AtualizarDescricaoViewModel(
    private val repository: IDescricaoRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
):ViewModel() {
    fun atualizarDescricao(descricao: String,onResult: (Boolean) -> Unit){
        Log.d("Descricao", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                var idUser = sharedPreferencesHelper.getIdUser()
                var authToken = sharedPreferencesHelper.getAuthToken()
                var response = repository.atualizarDescricao(authToken, idUser, formDescricao(descricao))
                if (response.isSuccessful) {
                    Log.d("Descricao", "SUCESSO")
                    onResult(true)
                    sharedPreferencesHelper.saveDescricao(descricao)
                } else {
                    Log.d("Descricao", "FALHA")
                    onResult(false)
                }
            }catch (e:Exception){
                Log.d("Descricao", "Exception:::$e")
                onResult(false)
            }
        }
    }
}