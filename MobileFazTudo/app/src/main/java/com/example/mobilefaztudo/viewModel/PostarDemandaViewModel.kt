package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.formDataDemanda
import com.example.mobilefaztudo.repository.IPostarDemandaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class PostarDemandaViewModel (
    private val repository : IPostarDemandaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
):ViewModel(){

    fun postarDemanda(idUser: Int,
                      id: Int,
                      fkContractor:Int,
                      fkProvider:Int,
                      descricao:String,
                      avaliacao:String,
                      status:String,
                      categoria:Int,
                      rating:Int,
                      dataCriacao: String,
                      dataDeConclusao: String,
                      imageBase64:String,
                      onResult: (Boolean, Int)-> Unit,
                      ){

        Log.d("POSTAR DEMANDA", "CHAMOU A VIEWMODEL")

        viewModelScope.launch { 
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.postarDemanda(authToken,idUser,
                    formDataDemanda(id,fkContractor,fkProvider,descricao,avaliacao,status,categoria,rating,dataCriacao,dataDeConclusao,imageBase64))
            if (response.isSuccessful){
                response.body()?.let { post ->
                    onResult(true, post.id)
                    Log.d("POSTAR DEMANDA", "SUCESSO")
                    Log.d("POSTAR DEMANDA", "${response.body()}")
                } ?: run{
                    onResult(false, 0)
                    Log.d("POSTAR DEMANDA", "FALHA")
                }
                Log.d("POSTAR DEMANDA", "SUCESSO")
            }
            }catch (e: Exception){
                onResult(false,0)
                Log.d("POSTAR DEMANDA", "EXCEPTION:::${e}")
            }
        }
    }
}