package com.example.mobilefaztudo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilefaztudo.api.CadastroContratanteBody
import com.example.mobilefaztudo.repository.ICadastroContratanteRepository
import kotlinx.coroutines.launch

class CadastroContratanteViewModel (private val repository :ICadastroContratanteRepository): ViewModel(){
    fun registerContractor(
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
         proUser: Boolean = false,
         onResult: (Boolean) -> Unit){
        Log.d("CADASTRO", "CHAMOU A VIEWMODEL")
        viewModelScope.launch {
            try {
                val response = repository.registerContractor(
                    CadastroContratanteBody(name,lastName,cpf,dt_nascimento,cep,logradouro,state, city,phone,email,senha, proUser)
                )
                if (response.isSuccessful) {
                    onResult(true)
                    Log.d("CADASTRO-CONTRATANTE", "SUCESSO")

                } else {
                    onResult(false)
                    Log.d("CADASTRO-CONTRATANTE", "ALGUM DADO INSERIDO ERRADO")
                }
            } catch (e: Exception) {
                Log.d("CADASTRO-CONTRATANTE", "exception ::: $e")
                onResult(false)
            }
        }
         }
}