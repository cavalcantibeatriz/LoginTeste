package com.example.mobilefaztudo.viewModel.Contratante

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IGetAceitarInteresseRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class AceitarInteresseViewModel(
    private val repository: IGetAceitarInteresseRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel (){

    fun aceitarInteresse(idItem: Int, onResult:(Boolean)-> Unit) {
        Log.d("ACEITAR", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.aceitarInteresse(authToken, idItem)
                if (response.isSuccessful) {
                    onResult(true)
                    Log.d("ACEITAR", "SUCESSO AO ACEITAR")
                } else {
                    onResult(false)
                    Log.d("ACEITAR", "FALHA AO ACEITAR")
                }
            }catch(e:Exception){
                onResult(false)
                Log.d("ACEITAR", "EXCEPTION::: ${e}")
            }
        }
    }


}