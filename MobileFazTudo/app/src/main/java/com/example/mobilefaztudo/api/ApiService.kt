package com.example.mobilefaztudo.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestBody): Response<LoginResponse>

    @POST("auth/register-contractor")
    suspend fun registerContractor(@Body body: CadastroContratanteBody): Response<CadastroContratanteResponse>

    @POST("auth/register-service-provider")
    suspend fun registerProvider(@Body body: CadastroPrestadorBody): Response<CadastroPrestadorResponse>

    @DELETE("favorite/{idContratante}/{idUser}")
    suspend fun deleteFavorite(
        @Header("Authorization") authToken: String,
        @Path("idContratante") idContratante: Int,
        @Path("idUser") idUser: Int
    ): Response<Unit>

    @POST("favorite/{idContratante}/{idUser}")
    suspend fun postFavorite(
        @Header("Authorization") authToken: String,
        @Path("idContratante") idContratante: Int,
        @Path("idUser") idUser: Int
    ): Response<Unit>

    @POST("proposta/criar/{idDemanda}/{idUser}")
    suspend fun enviarMensagem(
        @Header("Authorization") authToken: String,
        @Path("idDemanda") idDemanda: Int,
        @Path("idUser") idUser: Int,
        @Body body: MensagemRequest
        ): Response<Unit>

    @GET("search/")
        suspend fun listProviders(@Header("Authorization") authToken: String): Response<List<User>>

    @GET("post/all")
    suspend fun listDemandas(@Header("Authorization") authToken: String): Response<List<Demanda>>

}

data class LoginRequestBody(
    val email: String,
    val senha: String
)

data class LoginResponse(
    val token: String,
    val login: User
)
data class User(
    val id: Int,
    val name: String,
    val lastName: String,
    val cpf: String,
    val dt_nascimento: String,
    val cep: String,
    val logradouro: String,
    val state: String,
    val city: String,
    val phone: String,
    val email: String,
    val senha: String,
    val dt_cadastro: String,
    val descricao: String?,
    val image_profile: String?,
    val role: String?,
    val category: Category?,
    val proUser: Boolean,
    val password: String,
    val enabled: Boolean,
    val username: String,
    val authorities: List<Authorities>,
    val accountNonExpired: Boolean,
    val credentialsNonExpired: Boolean,
    val accountNonLocked: Boolean
)

data class Authorities(
    val authority: String
)

data class CadastroContratanteBody(
    val name: String,
    val lastName: String,
    val cpf: String,
    val dt_nascimento: String,
    val cep: String,
    val logradouro: String,
    val state: String,
    val city: String,
    val phone: String,
    val email: String,
    val senha: String,
    val proUser: Boolean = false
)

data class CadastroContratanteResponse(
    val id: Int,
    val name: String,
    val lastName: String,
    val cpf: String,
    val dt_nascimento: String,
    val cep: String,
    val logradouro: String,
    val state: String,
    val city: String,
    val phone: String,
    val email: String,
    val senha: String,
    val dt_cadastro: String,
    val descricao: String?,
    val image_profile: String?,
    val role: String?,
    val proUser: Boolean,
    val password: String,
    val enabled: Boolean,
    val username: String,
    val authorities: List<Authorities>,
    val accountNonExpired: Boolean,
    val credentialsNonExpired: Boolean,
    val accountNonLocked: Boolean
)

data class CadastroPrestadorBody(
    val name: String,
    val lastName: String,
    val cpf: String,
    val dt_nascimento: String,
    val cep: String,
    val logradouro: String,
    val state: String,
    val city: String,
    val phone: String,
    val email: String,
    val senha: String,
    val category: Category
)
data class CadastroPrestadorResponse(
    val id: Int,
    val name: String,
    val lastName: String,
    val cpf: String,
    val dt_nascimento: String,
    val cep: String,
    val logradouro: String,
    val state: String,
    val city: String,
    val phone: String,
    val email: String,
    val senha: String,
    val dt_cadastro: String,
    val descricao: String?,
    val image_profile: String?,
    val role: String?,
    val category: Category,
    val password: String,
    val enabled: Boolean,
    val authorities: List<Authorities>,
    val username: String,
    val accountNonLocked: Boolean,
    val accountNonExpired: Boolean,
    val credentialsNonExpired: Boolean
)

data class Category(
    val id: Int,
    val name: String
)

data class Demanda(
    val id: Int,
    val fkContractor: Int,
    val fkProvider: Int,
    val descricao: String,
    val avaliacao: String,
    val status: String,
    val categoria: Int,
    val rating: Int,
    val dataCriacao: String,
    val dataDeConclusao : String?,
    val data: String?
)

data class MensagemRequest(
    val mensagem: String,
    val post: Demanda,
    val prestador: User
    )