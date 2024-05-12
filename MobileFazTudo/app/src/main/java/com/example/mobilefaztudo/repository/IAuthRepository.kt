package com.example.mobilefaztudo.repository

import com.example.mobilefaztudo.api.CadastroContratanteBody
import com.example.mobilefaztudo.api.CadastroContratanteResponse
import com.example.mobilefaztudo.api.CadastroPrestadorBody
import com.example.mobilefaztudo.api.CadastroPrestadorResponse
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.LoginResponse
import retrofit2.Response
import retrofit2.http.Body

interface ILoginRepository {
    suspend fun login(@Body body: LoginRequestBody): Response<LoginResponse>
}

interface ICadastroPrestadorRepository {
    suspend fun registerProvider(@Body body: CadastroPrestadorBody): Response<CadastroPrestadorResponse>
}

interface ICadastroContratanteRepository {
    suspend fun registerContractor(@Body body: CadastroContratanteBody): Response<CadastroContratanteResponse>
}
