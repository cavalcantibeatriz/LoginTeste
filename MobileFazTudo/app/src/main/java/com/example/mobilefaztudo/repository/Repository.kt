package com.example.mobilefaztudo.repository

import android.content.Context
import android.media.Image
import android.net.Uri
import com.example.mobilefaztudo.api.ApiService
import com.example.mobilefaztudo.api.CadastroContratanteBody
import com.example.mobilefaztudo.api.CadastroContratanteResponse
import com.example.mobilefaztudo.api.CadastroPrestadorBody
import com.example.mobilefaztudo.api.CadastroPrestadorResponse
import com.example.mobilefaztudo.api.Demanda
import com.example.mobilefaztudo.api.DemandaInteresse
import com.example.mobilefaztudo.api.ImagensResponse
import com.example.mobilefaztudo.api.LoginRequestBody
import com.example.mobilefaztudo.api.LoginResponse
import com.example.mobilefaztudo.api.MensagemRequest
import com.example.mobilefaztudo.api.RetrofitClient
import com.example.mobilefaztudo.api.UploadImage
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.api.formDataDemanda
import com.example.mobilefaztudo.api.formDataEmail
import com.example.mobilefaztudo.api.formDescricao
import com.example.mobilefaztudo.api.formInfoContratante
import com.example.mobilefaztudo.api.formInfoPrestador
import com.example.mobilefaztudo.api.formSenha
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

class ListDemandasUser : IListDemandasUser {
    override suspend fun listDemandasUser(
        authToken: String,
        idUser: Int
    ): Response<List<Demanda>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.listDemandasUser("Bearer $authToken", idUser)
    }
}

class ListDemandaAbertaRepository : IListDemandaAberta {
    override suspend fun listDemandaAberta(authToken: String): Response<List<DemandaInteresse>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.listDemandaAberta("Bearer $authToken")
    }
}

class ListNotificarInteresseRepository : IListNotificarInteresseRepository {
    override suspend fun listNotificarInteresse(authToken: String): Response<List<DemandaInteresse>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.listNotificarInteresse("Bearer $authToken")
    }
}

class GetAceitarInteresseRepository : IGetAceitarInteresseRepository {
    override suspend fun aceitarInteresse(authToken: String, idItem: Int): Response<Void> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.aceitarInteresse("Bearer $authToken", idItem)
    }
}

class GetNegarInteresseRepository : IGetNegarInteresseRepository {
    override suspend fun negarInteresse(authToken: String, idItem: Int): Response<Void> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.negarInteresse("Bearer $authToken", idItem)
    }
}

class EnviarEmailRepository : IEnviarEmailRepository {
    override suspend fun enviarEmail(authToken: String, body: formDataEmail): Response<Void> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.enviarEmail("Bearer $authToken", body)
    }
}

class PostarDemandaRepository : IPostarDemandaRepository {
    override suspend fun postarDemanda(
        authToken: String,
        idUser: Int,
        body: formDataDemanda
    ): Response<formDataDemanda> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.postDemanda("Bearer $authToken", idUser, body)
    }
}

class UpdateImgDemandaRepository : IUpdateImgDemandaRepository {
    override suspend fun atrelarDemanda(
        authToken: String,
        idPost: Int,
        file: MultipartBody.Part
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.atrelarDemanda("Bearer $authToken", idPost, file)
    }
}

class UpdateImgPerfilRepository : IUpdateImgPerfilRepository{
    override suspend fun atualizarImgPerfil(
        authToken: String,
        idUser: Int,
        file: MultipartBody.Part
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.atualizarImgPerfil("Bearer $authToken", idUser, file)
    }
}
class UpdateSenhaRepository:IUpdateSenhaRepository{
    override suspend fun atualizarSenha(
        authToken: String,
        idUser: Int,
        body: formSenha
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.atualizarSenha("Bearer $authToken", idUser, body)
    }
}

class UpdateInfoPrestadorRepository:IUpdateInfoPrestadorRepository{
    override suspend fun atualizarInformacoesPrestador(authToken: String, idUser: Int, body: formInfoPrestador): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.atualizarInformacoesPrestador("Bearer $authToken", idUser, body)    }
}

class UpdateInfoContratanteRepository:IUpdateInfoContratanteRepository{
    override suspend fun atualizarInformacoesContratante(
        authToken: String,
        idUser: Int,
        body: formInfoContratante
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.atualizarInformacoesContratante("Bearer $authToken", idUser, body)    }
}

class DescricaoRepository:IDescricaoRepository{
    override suspend fun atualizarDescricao(
        authToken: String,
        idUser: Int,
        body: formDescricao
    ): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.atualizarDescricao("Bearer $authToken", idUser, body)    }
}
class AnexarGaleriaRepository:IAnexarGaleriaRepository{
    override suspend fun anexarImagemGaleria(authToken: String, idUser: Int, file: MultipartBody.Part): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.anexarImagemGaleria("Bearer $authToken", idUser,file)    }
}

class GetGaleriaRepository:IGetGaleriaRepository{
    override suspend fun getGaleria(authToken: String, idUser: Int): Response<List<ImagensResponse>> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.getGaleria("Bearer $authToken", idUser)
    }
}
class DeleteGaleriaRepository:IDeleteGaleriaRepository{
    override suspend fun deleteGaleria(authToken: String, idImg: Int): Response<Unit> {
        val api = RetrofitClient.getInstance()
            .create(ApiService::class.java)
        return api.deleteGaleria("Bearer $authToken",idImg)    }
}
