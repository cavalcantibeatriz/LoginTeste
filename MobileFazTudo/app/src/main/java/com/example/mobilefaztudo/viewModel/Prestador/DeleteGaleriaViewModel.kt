package com.example.mobilefaztudo.viewModel.Prestador

import android.util.Log
import androidx.appcompat.widget.ShareActionProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IDeleteGaleriaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class DeleteGaleriaViewModel(
    private val repository: IDeleteGaleriaRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
):ViewModel() {
    fun deletar(idImg:Int, onResult : (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                var authToken = sharedPreferencesHelper.getAuthToken()
                var response = repository.deleteGaleria(authToken,idImg)
                if (response.isSuccessful){
                    onResult(true)
                    Log.d("DELETAR","SUCESSO")
                }else{
                    onResult(false)
                    Log.d("DELETAR","FALHA")
                }
            }catch (e:Exception){
                Log.d("DELETAR","Exception::$e")
            }
        }
    }
}