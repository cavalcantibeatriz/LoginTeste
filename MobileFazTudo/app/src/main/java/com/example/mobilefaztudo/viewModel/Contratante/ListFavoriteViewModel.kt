package com.example.mobilefaztudo.viewModel.Contratante

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.repository.IListProvidersFavoriteRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class ListFavoriteViewModel (private val repository : IListProvidersFavoriteRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper): ViewModel(){

    val listPrestadoresFavorite = MutableLiveData<List<User>>()
    fun listarPrestadoresFavoritos(){
        Log.d("Listar favoritos", "chamou view model")

        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val userId = sharedPreferencesHelper.getIdUser()
                val response = repository.listProvidersFavorite(authToken, userId)
                if (response.isSuccessful){
                    Log.d("Listar favoritos", "sucesso na listagem de favoritos")
                    listPrestadoresFavorite.value = response.body()
                }else{
                    Log.d("Listar favoritos", "falha na listagem de favoritos")
                }
            }catch (e:Exception){
                Log.d("Listar favoritos", "exception::: ${e}")

            }
        }
    }
}