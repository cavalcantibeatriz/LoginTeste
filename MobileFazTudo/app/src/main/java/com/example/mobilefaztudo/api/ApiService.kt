package com.example.mobilefaztudo.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.LocalDate

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestBody): Response<LoginResponse>

    @POST("auth/register-contractor")
    suspend fun registerContractor(@Body body: CadastroContratanteBody): Response<CadastroContratanteResponse>

    @POST("auth/register-service-provider")
    suspend fun registerProvider(@Body body: CadastroPrestadorBody): Response<CadastroPrestadorResponse>

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