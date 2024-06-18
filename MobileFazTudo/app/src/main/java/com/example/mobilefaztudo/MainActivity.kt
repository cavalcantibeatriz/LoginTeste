package com.example.mobilefaztudo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilefaztudo.repository.AnexarGaleriaRepository
import com.example.mobilefaztudo.repository.DeleteFavorite
import com.example.mobilefaztudo.repository.DeleteGaleriaRepository
import com.example.mobilefaztudo.repository.DescricaoRepository
import com.example.mobilefaztudo.repository.EnviarEmailRepository
import com.example.mobilefaztudo.repository.EnviarMensagemRepository
import com.example.mobilefaztudo.repository.GetAceitarInteresseRepository
import com.example.mobilefaztudo.repository.GetGaleriaRepository
import com.example.mobilefaztudo.repository.GetNegarInteresseRepository
import com.example.mobilefaztudo.repository.ListDemandaAbertaRepository
import com.example.mobilefaztudo.repository.ListDemandasRepository
import com.example.mobilefaztudo.repository.ListDemandasUser
import com.example.mobilefaztudo.repository.ListNotificarInteresseRepository
import com.example.mobilefaztudo.repository.ListProviders
import com.example.mobilefaztudo.repository.ListProvidersFavorite
import com.example.mobilefaztudo.repository.LoginRepository
import com.example.mobilefaztudo.repository.PostFavorite
import com.example.mobilefaztudo.repository.PostarDemandaRepository
import com.example.mobilefaztudo.repository.RegisterContractorRepository
import com.example.mobilefaztudo.repository.RegisterProviderRepository
import com.example.mobilefaztudo.repository.UpdateImgDemandaRepository
import com.example.mobilefaztudo.repository.UpdateImgPerfilRepository
import com.example.mobilefaztudo.repository.UpdateInfoContratanteRepository
import com.example.mobilefaztudo.repository.UpdateInfoPrestadorRepository
import com.example.mobilefaztudo.repository.UpdateSenhaRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.MobileFazTudoTheme
import com.example.mobilefaztudo.view.LoginECadastro.CadastroContratanteEtapa2
import com.example.mobilefaztudo.view.LoginECadastro.CadastroPrestadorEtapa2
import com.example.mobilefaztudo.view.LoginECadastro.CadastroScreenEtapa1
import com.example.mobilefaztudo.view.FavoritosScreen
import com.example.mobilefaztudo.view.LoginECadastro.LoginScreen
import com.example.mobilefaztudo.view.LoginECadastro.SplashScreen
import com.example.mobilefaztudo.view.TelaAcompanhamento.DemandContratante
import com.example.mobilefaztudo.view.TelaAcompanhamento.DemandPrestador
import com.example.mobilefaztudo.view.TelasNotificação.NotificacoesContratanteScreen
import com.example.mobilefaztudo.view.TelasNotificação.NotificacoesPrestadorScreen
import com.example.mobilefaztudo.view.TelasPerfil.PerfilContratanteScreen
import com.example.mobilefaztudo.view.TelasPerfil.PerfilPrestadorScreen
import com.example.mobilefaztudo.view.TelaHome.encontreDemandas
import com.example.mobilefaztudo.view.TelaHome.encontrePrestadores
import com.example.mobilefaztudo.viewModel.Contratante.AtrelarImagemDemandaViewModel
import com.example.mobilefaztudo.viewModel.Ambos.AtualizarPerfilViewModel
import com.example.mobilefaztudo.viewModel.Ambos.AtualizarSenhaViewModel
import com.example.mobilefaztudo.viewModel.Prestador.AnexarGaleriaViewModel
import com.example.mobilefaztudo.viewModel.Contratante.AceitarInteresseViewModel
import com.example.mobilefaztudo.viewModel.Contratante.AtualizarDescricaoViewModel
import com.example.mobilefaztudo.viewModel.Contratante.AtualizarInfoContratanteViewModel
import com.example.mobilefaztudo.viewModel.auth.CadastroContratanteViewModel
import com.example.mobilefaztudo.viewModel.auth.CadastroPrestadorViewModel
import com.example.mobilefaztudo.viewModel.Contratante.DesfavoritarViewModel
import com.example.mobilefaztudo.viewModel.Contratante.FavoritarViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListFavoriteViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListPrestadoresViewModel
import com.example.mobilefaztudo.viewModel.EnviarEmailViewModel
import com.example.mobilefaztudo.viewModel.EnviarMensagensViewModel
import com.example.mobilefaztudo.viewModel.Contratante.NegarInteresseViewModel
import com.example.mobilefaztudo.viewModel.NotificarInteresseViewModel
import com.example.mobilefaztudo.viewModel.Contratante.PostarDemandaViewModel
import com.example.mobilefaztudo.viewModel.GetGaleriaViewModel
import com.example.mobilefaztudo.viewModel.Prestador.AtualizarInfoPrestadorViewModel
import com.example.mobilefaztudo.viewModel.Prestador.DeleteGaleriaViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandaAbertasViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasUserViewModel
import com.example.mobilefaztudo.viewModel.auth.LoginViewModel

class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var prestadorViewModel: CadastroPrestadorViewModel
    private lateinit var contratanteViewModel: CadastroContratanteViewModel
    private lateinit var listPrestadoresViewModel: ListPrestadoresViewModel
    private lateinit var listDemandasViewModel: ListDemandasViewModel
    private lateinit var favoritarViewModel: FavoritarViewModel
    private lateinit var desfavoritarViewModel: DesfavoritarViewModel
    private lateinit var listPrestadoresFavoritos: ListFavoriteViewModel
    private lateinit var listDemandasUserViewModel: ListDemandasUserViewModel
    private lateinit var listDemandaAbertaViewModel : ListDemandaAbertasViewModel
    private lateinit var requestEnviarMensagem : EnviarMensagensViewModel
    private lateinit var listNotificarInteresseViewModel: NotificarInteresseViewModel
    private lateinit var negarInteresseViewModel: NegarInteresseViewModel
    private lateinit var aceitarInteresseViewModel: AceitarInteresseViewModel
    private lateinit var enviarEmailViewModel: EnviarEmailViewModel
    private lateinit var postarDemandaViewModel: PostarDemandaViewModel
    private lateinit var atrelarImagemDemandaViewModel : AtrelarImagemDemandaViewModel
    private lateinit var atualizarImgPerfilViewModel : AtualizarPerfilViewModel
    private lateinit var atualizarSenhaViewModel : AtualizarSenhaViewModel
    private lateinit var atualizarInfoContratanteViewModel: AtualizarInfoContratanteViewModel
    private lateinit var atualizarInfoPrestadorViewModel: AtualizarInfoPrestadorViewModel
    private lateinit var atualizarDescricaoViewModel: AtualizarDescricaoViewModel
    private lateinit var anexarGaleriaViewModel : AnexarGaleriaViewModel
    private lateinit var getGaleriaViewModel: GetGaleriaViewModel
    private lateinit var deleteGaleriaViewModel: DeleteGaleriaViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)

        val repositoryL = LoginRepository()
        loginViewModel = LoginViewModel(repositoryL, sharedPreferencesHelper)

        val repositoryC = RegisterContractorRepository()
        contratanteViewModel = CadastroContratanteViewModel(repositoryC)

        val repositoryP = RegisterProviderRepository()
        prestadorViewModel = CadastroPrestadorViewModel(repositoryP)

        val repositoryListP = ListProviders()
        listPrestadoresViewModel = ListPrestadoresViewModel(repositoryListP, sharedPreferencesHelper)

        val repositoryListD = ListDemandasRepository()
        listDemandasViewModel = ListDemandasViewModel(repositoryListD, sharedPreferencesHelper)

        val repositoryFavorite = PostFavorite()
        favoritarViewModel = FavoritarViewModel(repositoryFavorite, sharedPreferencesHelper)

        val repositoryDesfavorite = DeleteFavorite()
        desfavoritarViewModel = DesfavoritarViewModel(repositoryDesfavorite, sharedPreferencesHelper)

        val repositoryListFavorites = ListProvidersFavorite()
        listPrestadoresFavoritos = ListFavoriteViewModel(repositoryListFavorites,sharedPreferencesHelper)

        val respositoryListDemandasUser = ListDemandasUser()
        listDemandasUserViewModel = ListDemandasUserViewModel(respositoryListDemandasUser, sharedPreferencesHelper)

        val repositoryListDemandaAbertas = ListDemandaAbertaRepository()
        listDemandaAbertaViewModel = ListDemandaAbertasViewModel(repositoryListDemandaAbertas,sharedPreferencesHelper)

        val repositoryEnviarMensagem = EnviarMensagemRepository()
        requestEnviarMensagem = EnviarMensagensViewModel(repositoryEnviarMensagem,sharedPreferencesHelper)

        val repositoryListNotificar = ListNotificarInteresseRepository()
        listNotificarInteresseViewModel = NotificarInteresseViewModel(repositoryListNotificar, sharedPreferencesHelper)

        val repositoryNegarInteresse = GetNegarInteresseRepository()
        negarInteresseViewModel = NegarInteresseViewModel(repositoryNegarInteresse,sharedPreferencesHelper)

        val repositoryAceitarInteresse = GetAceitarInteresseRepository()
        aceitarInteresseViewModel = AceitarInteresseViewModel(repositoryAceitarInteresse, sharedPreferencesHelper)

        val repositoryEnviarEmail = EnviarEmailRepository()
        enviarEmailViewModel = EnviarEmailViewModel(repositoryEnviarEmail,sharedPreferencesHelper)

        val repositoryPostarDemanda = PostarDemandaRepository()
        postarDemandaViewModel = PostarDemandaViewModel(repositoryPostarDemanda,sharedPreferencesHelper)

        val repositoryAtrelarImagemDemanda = UpdateImgDemandaRepository()
        atrelarImagemDemandaViewModel = AtrelarImagemDemandaViewModel(repositoryAtrelarImagemDemanda,sharedPreferencesHelper)

        val repositoryAtualizarImgPerfil = UpdateImgPerfilRepository()
        atualizarImgPerfilViewModel =
            AtualizarPerfilViewModel(repositoryAtualizarImgPerfil, sharedPreferencesHelper)

        val repositoryAtualizarSenha = UpdateSenhaRepository()
        atualizarSenhaViewModel = AtualizarSenhaViewModel(repositoryAtualizarSenha, sharedPreferencesHelper)

        val repositoryAtualizarInfoContratante = UpdateInfoContratanteRepository()
        atualizarInfoContratanteViewModel = AtualizarInfoContratanteViewModel(repositoryAtualizarInfoContratante, sharedPreferencesHelper)

        val repositoryAtualizarInfoPrestador = UpdateInfoPrestadorRepository()
        atualizarInfoPrestadorViewModel = AtualizarInfoPrestadorViewModel(repositoryAtualizarInfoPrestador, sharedPreferencesHelper)

        val repositoryAtualizarDescricao = DescricaoRepository()
        atualizarDescricaoViewModel = AtualizarDescricaoViewModel(repositoryAtualizarDescricao,sharedPreferencesHelper)

        val repositoryGetGaleria = GetGaleriaRepository()
        getGaleriaViewModel = GetGaleriaViewModel(repositoryGetGaleria,sharedPreferencesHelper)

        val repositoryAnexarGaleria = AnexarGaleriaRepository()
        anexarGaleriaViewModel= AnexarGaleriaViewModel(repositoryAnexarGaleria,sharedPreferencesHelper)

        val repositoryDeleteGaleria = DeleteGaleriaRepository()
        deleteGaleriaViewModel = DeleteGaleriaViewModel(repositoryDeleteGaleria,sharedPreferencesHelper)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileFazTudoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable(route = "login") { LoginScreen(loginViewModel, navController) }
                    composable(route = "cadastro1") { CadastroScreenEtapa1(navController) }
                    composable(route = "cadastro2C") { CadastroContratanteEtapa2(contratanteViewModel, navController) }
                    composable(route = "cadastro2P") { CadastroPrestadorEtapa2(prestadorViewModel, navController) }
                    composable(route = "splash") { SplashScreen(navController) }
                    composable(route = "encontrePrestadores") { encontrePrestadores(navController, listPrestadoresViewModel, sharedPreferencesHelper, favoritarViewModel, desfavoritarViewModel, listPrestadoresFavoritos,getGaleriaViewModel) }
                    composable(route = "encontreDemandas") { encontreDemandas(navController, listDemandasViewModel, sharedPreferencesHelper, requestEnviarMensagem) }
                    composable(route = "encontreFavoritos") { FavoritosScreen(navController, listPrestadoresFavoritos, sharedPreferencesHelper, favoritarViewModel, desfavoritarViewModel, listPrestadoresFavoritos, listPrestadoresViewModel,getGaleriaViewModel) }
                    composable(route = "PerfilPrestadorScreen") { PerfilPrestadorScreen(navController, sharedPreferencesHelper, atualizarImgPerfilViewModel,atualizarSenhaViewModel,atualizarInfoPrestadorViewModel, atualizarDescricaoViewModel,getGaleriaViewModel,anexarGaleriaViewModel,deleteGaleriaViewModel) }
                    composable(route = "PerfilContratanteScreen") { PerfilContratanteScreen(navController, sharedPreferencesHelper, atualizarImgPerfilViewModel, listDemandasUserViewModel,atualizarSenhaViewModel, atualizarInfoContratanteViewModel) }
                    composable(route = "DemandContratante") { DemandContratante(navController, sharedPreferencesHelper,listDemandasUserViewModel, postarDemandaViewModel, atrelarImagemDemandaViewModel) }
                    composable(route = "DemandPrestador") { DemandPrestador(navController, sharedPreferencesHelper,listDemandasViewModel,listDemandaAbertaViewModel) }
                    composable(route = "NotificacoesContratanteScreen") { NotificacoesContratanteScreen(navController, sharedPreferencesHelper, listNotificarInteresseViewModel,negarInteresseViewModel, aceitarInteresseViewModel, enviarEmailViewModel) }
                    composable(route = "NotificacoesPrestadorScreen") { NotificacoesPrestadorScreen(navController, sharedPreferencesHelper, listNotificarInteresseViewModel,enviarEmailViewModel) }
                }
            }
        }
    }
}


