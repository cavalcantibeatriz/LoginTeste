package com.example.mobilefaztudo.viewModel.Prestador

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.Demanda
import com.example.mobilefaztudo.repository.IListDemandasUser
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class ListDemandasUserViewModel(
    private val repository: IListDemandasUser,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    val listDemandasUser = MutableLiveData<List<Demanda>>()

    fun listarDemandasUser() {
        Log.d("LISTAR DEMANDAS", "CHAMOU A VIEWMODEL")

        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val idUser = sharedPreferencesHelper.getIdUser()
                val response = repository.listDemandasUser(authToken, idUser)
                if (response.isSuccessful) {
                    Log.d("LISTAR DEMANDAS", "sucesso no retorno")
                    listDemandasUser.value = response.body()
                } else {
                    Log.d("LISTAR DEMANDAS", "falha no retorno")
                }
            } catch (e: Exception) {
                Log.d("LISTAR DEMANDAS", "exception ${e}")
            }
        }
    }


}