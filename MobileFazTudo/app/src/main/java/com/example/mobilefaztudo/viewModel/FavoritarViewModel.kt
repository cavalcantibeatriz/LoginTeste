//package com.example.mobilefaztudo.viewModel
//
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.mobilefaztudo.repository.IDeleteFavoriteRepository
//import com.example.mobilefaztudo.repository.IPostFavoriteRepository
//import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
//import kotlinx.coroutines.launch
//
////FAVORITAR
//class FavoritarViewModel(
//    private val repository: IPostFavoriteRepository,
//    private val sharedPreferencesHelper: SharedPreferencesHelper)
//    : ViewModel() {
//    fun postFavorite(idContratante: Int, idUser: Int, onResult: (Boolean) -> Unit) {
//        Log.d("EXIBIR", "CHAMOU A VIEWMODEL fav")
//        val idUser = sharedPreferencesHelper.getIdUser()
//        viewModelScope.launch {
//            try {
//                val authToken = sharedPreferencesHelper.getAuthToken()
//                val response = repository.postFavorite(authToken,idContratante, idUser)
//                if (response.isSuccessful) {
//                    onResult(true)
//                    Log.d("EXIBIR", "SUCESSO AO FAVORITAR")
//                } else {
//                    onResult(false)
//                    Log.d("EXIBIR", "FALHA AO FAVORITAR")
//                }
//            } catch (e: Exception) {
//                onResult(false)
//                Log.d("EXIBIR", "EXCEPTION AO FAVORITAR ${e}")
//            }
//        }
//    }
//}