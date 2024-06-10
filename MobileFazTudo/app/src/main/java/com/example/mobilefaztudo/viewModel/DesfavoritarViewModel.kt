package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IDeleteFavoriteRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

class DesfavoritarViewModel(
    private val repository: IDeleteFavoriteRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
)
    : ViewModel() {
    fun deleteFavorite(idContratante: Int, idProvider: Int, onResult: (Boolean) -> Unit) {
        Log.d("Favorite", "CHAMOU A VIEWMODEL delete fav")

        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.deleteFavorite(authToken,idContratante, idProvider)
                if (response.isSuccessful) {
                    Log.d("Favorite", "SUCESSO AO DESFAVORITAR")
                    onResult(true)
                } else {
                    Log.d("Favorite", "FALHA AO DESFAVORITAR")
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.d("Favorite", "EXCEPTION AO DESFAVORITAR ${e}")
                onResult(false)
            }
        }
    }
}