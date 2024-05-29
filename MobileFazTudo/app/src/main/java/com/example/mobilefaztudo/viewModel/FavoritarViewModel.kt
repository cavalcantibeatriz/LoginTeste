package com.example.mobilefaztudo.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.repository.IDeleteFavoriteRepository
import com.example.mobilefaztudo.repository.IPostFavoriteRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import kotlinx.coroutines.launch

//FAVORITAR
class FavoritarViewModel(private val repository: IPostFavoriteRepository) : ViewModel() {
    private val _postFavoriteResult = MutableLiveData<Result<Unit>>()
    val postFavoriteResult: LiveData<Result<Unit>> get() = _postFavoriteResult
    fun postFavorite(idContratante: Int, context: Context) {
        val sharedPreferencesHelper = SharedPreferencesHelper(context)
        val idUser = sharedPreferencesHelper.getIdUser()

        viewModelScope.launch {
            try {
                val response = repository.postFavorite(idContratante, idUser)
                if (response.isSuccessful) {
                    _postFavoriteResult.value = Result.success(Unit)
                } else {
                    _postFavoriteResult.value = Result.failure(Exception("Failed to post favorite"))
                }
            } catch (e: Exception) {
                _postFavoriteResult.value = Result.failure(e)
            }
        }
    }
}


//TIRAR FAVORITO
class DesfavoritarViewModel(private val repository: IDeleteFavoriteRepository) : ViewModel() {
    private val _deleteFavoriteResult = MutableLiveData<Result<Unit>>()
    val deleteFavoriteResult: LiveData<Result<Unit>> get() = _deleteFavoriteResult
    fun deleteFavorite(idContratante: Int, context: Context) {
        val sharedPreferencesHelper = SharedPreferencesHelper(context)
        val idUser = sharedPreferencesHelper.getIdUser()

        viewModelScope.launch {
            try {
                val response = repository.deleteFavorite(idContratante, idUser)
                if (response.isSuccessful) {
                    _deleteFavoriteResult.value = Result.success(Unit)
                } else {
                    _deleteFavoriteResult.value = Result.failure(Exception("Failed to delete favorite"))
                }
            } catch (e: Exception) {
                _deleteFavoriteResult.value = Result.failure(e)
            }
        }
    }
}