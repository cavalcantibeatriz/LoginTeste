package com.example.mobilefaztudo.viewModel.Contratante

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.formInfoContratante
import com.example.mobilefaztudo.repository.IUpdateInfoContratanteRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class AtualizarInfoContratanteViewModel(
    private val repository: IUpdateInfoContratanteRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
):ViewModel() {
    fun atualizarInformacoesContratante(cep:String,logradouro:String,state: String,city: String,phone: String, onResult : (Boolean) -> (Unit)){
        Log.d("Atualizar Infos", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                var idUser = sharedPreferencesHelper.getIdUser()
                var authToken = sharedPreferencesHelper.getAuthToken()
                var response = repository.atualizarInformacoesContratante(authToken,idUser, formInfoContratante(cep,logradouro,state,city,phone))
                Log.d("Atualizar Infos", "${formInfoContratante(cep,logradouro,state,city,phone)}")

                if (response.isSuccessful){
                    Log.d("Atualizar Infos", "SUCESSO")
                    onResult(true)
                }else{
                    Log.d("Atualizar Infos", "FALHA")
                    onResult(false)
                }
            }catch (e:Exception){
                Log.d("Atualizar Infos", "Exception:::$e")
                onResult(false)
            }
        }
    }
}