package com.example.mobilefaztudo.repository

import com.example.mobilefaztudo.api.ApiService
import com.example.mobilefaztudo.api.CadastroContratanteBody
import com.example.mobilefaztudo.api.CadastroContratanteResponse
import com.example.mobilefaztudo.api.CadastroPrestadorBody
import com.example.mobilefaztudo.api.CadastroPrestadorResponse
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.LoginResponse
import com.example.mobilefaztudo.api.RetrofitClient
import retrofit2.Response

class LoginRepository : ILoginRepository {

    override suspend fun login(body: LoginRequestBody): Response<LoginResponse> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)

        return api.login(body)
    }
}

class RegisterProviderRepository : ICadastroPrestadorRepository {
    override suspend fun registerProvider(body: CadastroPrestadorBody): Response<CadastroPrestadorResponse> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.registerProvider(body)
    }
}

class RegisterContractorRepository : ICadastroContratanteRepository {
    override suspend fun registerContractor(body: CadastroContratanteBody): Response<CadastroContratanteResponse> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)

        return api.registerContractor(body)
    }
}