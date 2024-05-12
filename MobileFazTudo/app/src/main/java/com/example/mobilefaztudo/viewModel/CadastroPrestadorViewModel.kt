package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.CadastroPrestadorBody
import com.example.mobilefaztudo.api.Category
import com.example.mobilefaztudo.repository.ICadastroPrestadorRepository
import com.example.mobilefaztudo.repository.ILoginRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class CadastroPrestadorViewModel(private val repository: ICadastroPrestadorRepository) : ViewModel() {
    fun registerProvider(
        name: String,
        lastName: String,
        cpf: String,
        dt_nascimento: String,
        cep: String,
        logradouro: String,
        state: String,
        city: String,
        phone: String,
        email: String,
        senha: String,
        category: Category,
        onResult: (Boolean) -> Unit) {
        Log.d("CADASTRO", "CHAMOU A VIEWMODEL")

        viewModelScope.launch {
            try {
                val response = repository.registerProvider(
                    CadastroPrestadorBody(name,lastName,cpf,dt_nascimento,cep,logradouro,state,city,phone,email, senha, category)
                )
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                Log.d("CADASTRO-PRESTADOR", "exception ::: $e")
                onResult(true)
            }
        }
    }
}