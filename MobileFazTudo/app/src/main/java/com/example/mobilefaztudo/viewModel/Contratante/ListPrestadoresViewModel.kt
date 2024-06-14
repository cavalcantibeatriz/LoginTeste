package com.example.mobilefaztudo.viewModel.Contratante

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.repository.IListProvidersRepository
import com.example.mobilefaztudo.repository.ILoginRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class ListPrestadoresViewModel(
    private val repository: IListProvidersRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper)
    : ViewModel() {

    val listPrestadores = MutableLiveData<List<User>>()

    fun listarPrestadores(){
        Log.d("LISTAR PRESTADORES", "CHAMOU A VIEWMODEL")

        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.listProviders(authToken)
                if (response.isSuccessful){
                    Log.d("LISTAR PRESTADORES", "sucesso no retorno")
                    listPrestadores.value = response.body()
                }else{
                    Log.d("LISTAR PRESTADORES", "falha no retorno")
                }
            }catch (e: Exception){
                Log.d("LISTAR PRESTADORES", "exception ${e}")
            }
        }
    }

}