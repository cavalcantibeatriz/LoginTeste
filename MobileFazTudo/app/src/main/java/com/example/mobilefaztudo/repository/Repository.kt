package com.example.mobilefaztudo.repository

import com.example.mobilefaztudo.api.ApiService
import com.example.mobilefaztudo.api.CadastroContratanteBody
import com.example.mobilefaztudo.api.CadastroContratanteResponse
import com.example.mobilefaztudo.api.CadastroPrestadorBody
import com.example.mobilefaztudo.api.CadastroPrestadorResponse
import com.example.mobilefaztudo.api.Demanda
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.LoginResponse
import com.example.mobilefaztudo.api.MensagemRequest
import com.example.mobilefaztudo.api.RetrofitClient
import com.example.mobilefaztudo.api.User
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

class PostFavorite : IPostFavoriteRepository {
    override suspend fun postFavorite(
        authToken: String,
        idContratante: Int,
        idProvider: Int
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.postFavorite("Bearer $authToken", idContratante, idProvider)
    }
}

class DeleteFavorite : IDeleteFavoriteRepository {
    override suspend fun deleteFavorite(
        authToken: String,
        idContratante: Int,
        idProvider: Int
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.deleteFavorite("Bearer $authToken", idContratante, idProvider)
    }
}
class ListProvidersFavorite : IListProvidersFavoriteRepository {
    override suspend fun listProvidersFavorite(
        authToken: String,
        idUser: Int
    ): Response<List<User>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.listProvidersFavorite("Bearer $authToken", idUser)
    }
}

class ListProviders : IListProvidersRepository {
    override suspend fun listProviders(authToken: String): Response<List<User>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.listProviders("Bearer $authToken")
    }
}

class ListDemandasRepository : IListDemandasRepository {
    override suspend fun listDemandas(authToken: String): Response<List<Demanda>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.listDemandas("Bearer $authToken")
    }
}

class EnviarMensagemRepository : IEnviarMensagemRepository {

    override suspend fun enviarMensagem(
        authToken: String,
        idDemanda: Int,
        idUser: Int,
        body: MensagemRequest
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.enviarMensagem("Bearer $authToken", idDemanda, idUser, body)
    }
}