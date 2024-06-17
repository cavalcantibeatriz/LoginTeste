package com.example.mobilefaztudo.viewModel.Prestador

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.Category
import com.example.mobilefaztudo.api.formInfoPrestador
import com.example.mobilefaztudo.repository.IUpdateInfoPrestadorRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class AtualizarInfoPrestadorViewModel(private val repository: IUpdateInfoPrestadorRepository, private val sharedPreferencesHelper: SharedPreferencesHelper): ViewModel() {
    fun atualizarInformacoesPrestador(
        cep:String,logradouro:String,state: String,city: String,phone: String, categoryId : Int, categoryName: String, onResult : (Boolean) -> (Unit)){
        Log.d("Atualizar Infos", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                var idUser = sharedPreferencesHelper.getIdUser()
                var authToken = sharedPreferencesHelper.getAuthToken()
                val body = formInfoPrestador(cep, logradouro, state, city, phone, Category(categoryId, categoryName))
                var response = repository.atualizarInformacoesPrestador(authToken,idUser, body)

                Log.d("Atualizar Infos", "Request Body: $body")
                Log.d("Atualizar Infos", "Response: $response")

                if (response.isSuccessful){
                    sharedPreferencesHelper.saveCep(cep)
                    sharedPreferencesHelper.saveCity(city)
                    sharedPreferencesHelper.saveState(state)
                    sharedPreferencesHelper.saveLogradouro(logradouro)
                    sharedPreferencesHelper.savePhone(phone)
                    sharedPreferencesHelper.saveCategoriaId(categoryId)
                    sharedPreferencesHelper.saveCategoriaName(categoryName)

                    Log.d("Atualizar Infos", "SUCESSO")
                    onResult(true)
                }else{
                    Log.d("Atualizar Infos", "FALHA: ${response.errorBody().toString()}")
                    onResult(false)
                }
            }catch (e:Exception){
                Log.d("Atualizar Infos", "Exception:::$e")
                onResult(false)
            }
        }
    }
}