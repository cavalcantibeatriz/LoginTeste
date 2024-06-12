package com.example.mobilefaztudo.viewModel.Prestador

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.DemandaInteresse
import com.example.mobilefaztudo.repository.IListDemandaAberta
import com.example.mobilefaztudo.repository.ListDemandaAbertaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class ListDemandaAbertasViewModel(
    private val repository: IListDemandaAberta,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    val listDemandasAbertas = MutableLiveData<List<DemandaInteresse>>()
    fun listarDemandaAberta(){
        Log.d("LISTAR DEMANDAS ABERTAS", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.listDemandaAberta(authToken)
                if (response.isSuccessful) {
                    Log.d("LISTAR DEMANDAS ABERTAS", "sucesso no retorno")
                    listDemandasAbertas.value = response.body()
                } else {
                    Log.d("LISTAR DEMANDAS ABERTAS", "falha no retorno")
                }
            } catch (e: Exception) {
                Log.d("LISTAR DEMANDAS ABERTAS", "exception ${e}")
            }
        }
    }

}