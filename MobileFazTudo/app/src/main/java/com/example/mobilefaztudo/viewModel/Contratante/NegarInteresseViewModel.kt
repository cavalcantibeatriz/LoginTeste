package com.example.mobilefaztudo.viewModel.Contratante

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IGetNegarInteresseRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class NegarInteresseViewModel(
    private val repository: IGetNegarInteresseRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    fun negarInteresse(idItem: Int, onResult: (Boolean) -> Unit) {
        Log.d("NEGAR", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.negarInteresse(authToken,idItem)
                if (response.isSuccessful){
                    onResult(true)
                    Log.d("NEGAR", "SUCESSO AO NEGAR")
                }else{
                    onResult(false)
                    Log.d("NEGAR", "FALHA AO NEGAR")
                }
            } catch (e: Exception) {
                onResult(false)
                Log.d("NEGAR", "Exception::: ${e.message}")
            }
        }
    }
}