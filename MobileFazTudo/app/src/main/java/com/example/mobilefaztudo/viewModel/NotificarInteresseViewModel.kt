package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.DemandaInteresse
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.repository.IListNotificarInteresseRepository
import com.example.mobilefaztudo.repository.ListNotificarInteresseRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class NotificarInteresseViewModel(
    private val repository: IListNotificarInteresseRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    val listInteresses = MutableLiveData<List<DemandaInteresse>>()

    fun listNotificar() {
        Log.d("NOTIFICAR", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.listNotificarInteresse(authToken)
                if (response.isSuccessful) {
                    Log.d("NOTIFICAR", "SUCESSO")
                    listInteresses.value = response.body()
                } else {
                    Log.d("NOTIFICAR", "FALHA NO RETORNO")
                }
            } catch (e: Exception) {
                Log.d("NOTIFICAR", "exception ::: $e")
            }
        }
    }
}