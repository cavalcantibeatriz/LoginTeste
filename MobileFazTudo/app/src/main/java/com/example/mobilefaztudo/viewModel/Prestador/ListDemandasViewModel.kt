package com.example.mobilefaztudo.viewModel.Prestador

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.Demanda
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.repository.ListDemandasRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class ListDemandasViewModel (
    private val repository: ListDemandasRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper)
    :ViewModel(){

    val listDemandas = MutableLiveData<List<Demanda>>()

    fun listarDemandas(){
        Log.d("LISTAR DEMANDAS", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.listDemandas(authToken)
                if (response.isSuccessful){
                    Log.d("LISTAR DEMANDAS", "sucesso no retorno")
                    listDemandas.value = response.body()
                }else{
                    Log.d("LISTAR DEMANDAS", "falha no retorno")
                }
            }catch (e: Exception){
                Log.d("LISTAR DEMANDAS", "exception ${e}")
            }
        }
    }

}