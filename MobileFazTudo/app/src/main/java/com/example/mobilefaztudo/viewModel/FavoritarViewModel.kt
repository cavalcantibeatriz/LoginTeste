package com.example.mobilefaztudo.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IDeleteFavoriteRepository
import com.example.mobilefaztudo.repository.IPostFavoriteRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

//FAVORITAR
class FavoritarViewModel(
    private val repository: IPostFavoriteRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper)
    : ViewModel() {
    fun postFavorite(idContratante: Int, idProvider: Int, onResult: (Boolean) -> Unit) {
        Log.d("Favorite", "CHAMOU A VIEWMODEL fav")
        viewModelScope.launch {
            try {
                val authToken = sharedPreferencesHelper.getAuthToken()
                val response = repository.postFavorite(authToken,idContratante, idProvider)
                if (response.isSuccessful) {
                    onResult(true)
                    Log.d("Favorite", "SUCESSO AO FAVORITAR")
                } else {
                    onResult(false)
                    Log.d("Favorite", "FALHA AO FAVORITAR")
                }
            } catch (e: Exception) {
                onResult(false)
                Log.d("Favorite", "EXCEPTION AO FAVORITAR ${e}")
            }
        }
    }
}