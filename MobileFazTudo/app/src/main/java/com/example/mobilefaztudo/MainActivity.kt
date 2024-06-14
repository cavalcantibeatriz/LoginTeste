package com.example.mobilefaztudo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilefaztudo.repository.DeleteFavorite
import com.example.mobilefaztudo.repository.EnviarMensagemRepository
import com.example.mobilefaztudo.repository.ListDemandaAbertaRepository
import com.example.mobilefaztudo.repository.ListDemandasRepository
import com.example.mobilefaztudo.repository.ListDemandasUser
import com.example.mobilefaztudo.repository.ListProviders
import com.example.mobilefaztudo.repository.ListProvidersFavorite
import com.example.mobilefaztudo.repository.LoginRepository
import com.example.mobilefaztudo.repository.PostFavorite
import com.example.mobilefaztudo.repository.RegisterContractorRepository
import com.example.mobilefaztudo.repository.RegisterProviderRepository
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
import com.example.mobilefaztudo.viewModel.auth.CadastroContratanteViewModel
import com.example.mobilefaztudo.viewModel.auth.CadastroPrestadorViewModel
import com.example.mobilefaztudo.viewModel.Contratante.DesfavoritarViewModel
import com.example.mobilefaztudo.viewModel.Contratante.FavoritarViewModel
import com.example.mobilefaztudo.viewModel.Prestador.ListDemandasViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListFavoriteViewModel
import com.example.mobilefaztudo.viewModel.Contratante.ListPrestadoresViewModel
import com.example.mobilefaztudo.viewModel.EnviarMensagensViewModel
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
    override fun onCreate(savedInstanceState: Bundle?) {
        val repositoryL = LoginRepository()
        val sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)

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

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileFazTudoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable(route = "login") { LoginScreen(loginViewModel, navController) }
                    composable(route = "cadastro1") { CadastroScreenEtapa1(navController) }
                    composable(route = "cadastro2C") {
                        CadastroContratanteEtapa2(
                            contratanteViewModel,
                            navController
                        )
                    }
                    composable(route = "cadastro2P") {
                        CadastroPrestadorEtapa2(
                            prestadorViewModel,
                            navController
                        )
                    }
                    composable(route = "splash") { SplashScreen(navController) }

                    composable(route = "encontrePrestadores") {
                        encontrePrestadores(
                            navController,
                            listPrestadoresViewModel,
                            sharedPreferencesHelper,
                            favoritarViewModel,
                            desfavoritarViewModel,
                            listPrestadoresFavoritos
                        )
                    }
                    composable(route = "encontreDemandas") {
                        encontreDemandas(
                            navController,
                            listDemandasViewModel,
                            sharedPreferencesHelper,
                            requestEnviarMensagem
                        )
                    }
                    composable(route = "encontreFavoritos") {
                        FavoritosScreen(
                            navController,
                            listPrestadoresFavoritos,
                            sharedPreferencesHelper,
                            favoritarViewModel,
                            desfavoritarViewModel,
                            listPrestadoresFavoritos,
                            listPrestadoresViewModel
                        )
                    }
                    composable(route = "PerfilPrestadorScreen") {
                        PerfilPrestadorScreen(navController, sharedPreferencesHelper)
                    }

                    composable(route = "PerfilContratanteScreen") {
                        PerfilContratanteScreen(navController, sharedPreferencesHelper)
                    }
                    composable(route = "DemandContratante") {
                        DemandContratante(navController, sharedPreferencesHelper,listDemandasUserViewModel)
                    }
                    composable(route = "DemandPrestador") {
                        DemandPrestador(navController, sharedPreferencesHelper,listDemandasViewModel,listDemandaAbertaViewModel)
                    }
                    composable(route = "NotificacoesContratanteScreen") {
                        NotificacoesContratanteScreen(navController, sharedPreferencesHelper)
                    }
                    composable(route = "NotificacoesPrestadorScreen") {
                        NotificacoesPrestadorScreen(navController, sharedPreferencesHelper)
                    }

                }
            }
        }
    }
}


