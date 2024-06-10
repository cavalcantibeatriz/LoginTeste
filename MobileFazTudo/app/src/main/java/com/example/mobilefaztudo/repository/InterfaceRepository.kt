package com.example.mobilefaztudo.repository

import com.example.mobilefaztudo.api.CadastroContratanteBody
import com.example.mobilefaztudo.api.CadastroContratanteResponse
import com.example.mobilefaztudo.api.CadastroPrestadorBody
import com.example.mobilefaztudo.api.CadastroPrestadorResponse
import com.example.mobilefaztudo.api.Demanda
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.LoginResponse
import com.example.mobilefaztudo.api.MensagemRequest
import com.example.mobilefaztudo.api.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

interface ILoginRepository {
    suspend fun login(@Body body: LoginRequestBody): Response<LoginResponse>
}

interface ICadastroPrestadorRepository {
    suspend fun registerProvider(@Body body: CadastroPrestadorBody): Response<CadastroPrestadorResponse>
}

interface ICadastroContratanteRepository {
    suspend fun registerContractor(@Body body: CadastroContratanteBody): Response<CadastroContratanteResponse>
}

interface IPostFavoriteRepository {
    suspend fun postFavorite(
        authToken: String,
        @Path("idContratante") idContratante: Int,
        @Path("idProvider") idProvider: Int
    ): Response<Unit>
}

interface IDeleteFavoriteRepository {
    suspend fun deleteFavorite(
        authToken: String,
        @Path("idContratante") idContratante: Int,
        @Path("idProvider") idProvider: Int
    ): Response<Unit>
}

interface IListProvidersRepository {
    suspend fun listProviders(authToken: String): Response<List<User>>
}

interface  IListProvidersFavoriteRepository{
    suspend fun listProvidersFavorite(
        authToken: String,
        @Path("idUser") idUser: Int
    ):Response<List<User>>
}

interface IListDemandasRepository {
    suspend fun listDemandas(authToken: String) : Response<List<Demanda>>
}

interface IEnviarMensagemRepository{
    suspend fun enviarMensagem(
        authToken: String,
        @Path ("idDemanda") idDemanda: Int,
        @Path("idUser") idUser: Int,
        @Body body: MensagemRequest
    ): Response<Unit>
}