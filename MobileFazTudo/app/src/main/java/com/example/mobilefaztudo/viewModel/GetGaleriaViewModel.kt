package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.ImagensResponse
import com.example.mobilefaztudo.repository.IGetGaleriaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class GetGaleriaViewModel(
    private val repository: IGetGaleriaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) :ViewModel(){
    val listImagesGaleria = MutableLiveData<List<ImagensResponse>>()
    fun getGaleria(idUser : Int){
        Log.d("LISTAR IMAGENS GALERIA", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.getGaleria(authToken,idUser)
                if (response.isSuccessful){
                    Log.d("LISTAR IMAGENS GALERIA", "SUCESSO")
                    listImagesGaleria.value = response.body()
                }else{
                    Log.d("LISTAR IMAGENS GALERIA", "FALHA")
                }
            }catch (e:Exception){
                Log.d("LISTAR IMAGENS GALERIA", "Exception::$e")
            }
        }
    }

    fun clearGaleria(){
        listImagesGaleria.value = emptyList()
    }
}