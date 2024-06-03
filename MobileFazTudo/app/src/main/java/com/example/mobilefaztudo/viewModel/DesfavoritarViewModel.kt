//package com.example.mobilefaztudo.viewModel
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.mobilefaztudo.repository.IDeleteFavoriteRepository
//import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
//import kotlinx.coroutines.launch
//
//class DesfavoritarViewModel(
//    private val repository: IDeleteFavoriteRepository,
//    private val sharedPreferencesHelper: SharedPreferencesHelper
//)
//    : ViewModel() {
//    fun deleteFavorite(idContratante: Int, idUser: Int, onResult: (Boolean) -> Unit) {
//        Log.d("EXIBIR", "CHAMOU A VIEWMODEL delete fav")
//        val idUser = sharedPreferencesHelper.getIdUser()
//
//        viewModelScope.launch {
//            try {
//                val authToken = sharedPreferencesHelper.getAuthToken()
//                val response = repository.deleteFavorite(authToken,idContratante, idUser)
//                if (response.isSuccessful) {
//                    Log.d("EXIBIR", "SUCESSO AO DESFAVORITAR")
//                    onResult(true)
//                } else {
//                    Log.d("EXIBIR", "FALHA AO DESFAVORITAR")
//                    onResult(false)
//                }
//            } catch (e: Exception) {
//                Log.d("EXIBIR", "EXCEPTION AO DESFAVORITAR ${e}")
//                onResult(false)
//            }
//        }
//    }
//}