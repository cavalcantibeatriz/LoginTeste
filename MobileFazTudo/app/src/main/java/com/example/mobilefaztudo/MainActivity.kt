package com.example.mobilefaztudo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilefaztudo.repository.ListDemandasRepository
import com.example.mobilefaztudo.repository.ListProviders
import com.example.mobilefaztudo.repository.LoginRepository
import com.example.mobilefaztudo.repository.RegisterContractorRepository
import com.example.mobilefaztudo.repository.RegisterProviderRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.MobileFazTudoTheme
import com.example.mobilefaztudo.view.Demand
import com.example.mobilefaztudo.view.LoginECadastro.CadastroContratanteEtapa2
import com.example.mobilefaztudo.view.LoginECadastro.CadastroPrestadorEtapa2
import com.example.mobilefaztudo.view.LoginECadastro.CadastroScreenEtapa1
import com.example.mobilefaztudo.view.FavoritosScreen
import com.example.mobilefaztudo.view.LoginECadastro.LoginScreen
import com.example.mobilefaztudo.view.LoginECadastro.SplashScreen
import com.example.mobilefaztudo.view.NotificacoesScreen
import com.example.mobilefaztudo.view.TelasPerfil.PerfilContratanteScreen
import com.example.mobilefaztudo.view.TelasPerfil.PerfilPrestadorScreen
import com.example.mobilefaztudo.view.encontreDemandas
import com.example.mobilefaztudo.view.encontrePrestadores
import com.example.mobilefaztudo.viewModel.CadastroContratanteViewModel
import com.example.mobilefaztudo.viewModel.CadastroPrestadorViewModel
import com.example.mobilefaztudo.viewModel.ListDemandasViewModel
import com.example.mobilefaztudo.viewModel.ListPrestadoresViewModel
import com.example.mobilefaztudo.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var prestadorViewModel: CadastroPrestadorViewModel
    private lateinit var contratanteViewModel: CadastroContratanteViewModel
    private lateinit var listPrestadoresViewModel: ListPrestadoresViewModel
    private lateinit var listDemandasViewModel: ListDemandasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val repositoryL = LoginRepository()
        val sharedPreferencesHelper = SharedPreferencesHelper(applicationContext)
        loginViewModel = LoginViewModel(repositoryL, sharedPreferencesHelper)

        val repositoryC = RegisterContractorRepository()
        contratanteViewModel = CadastroContratanteViewModel(repositoryC)

        val repositoryP = RegisterProviderRepository()
        prestadorViewModel = CadastroPrestadorViewModel(repositoryP)

        val repositoryListP = ListProviders()
        listPrestadoresViewModel =
            ListPrestadoresViewModel(repositoryListP, sharedPreferencesHelper)

        val repositoryListD = ListDemandasRepository()
        listDemandasViewModel = ListDemandasViewModel(repositoryListD, sharedPreferencesHelper)

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
                            sharedPreferencesHelper
                        )
                    }
                    composable(route = "encontreDemandas") {
                        encontreDemandas(
                            navController,
                            listDemandasViewModel,
                            sharedPreferencesHelper
                        )
                    }
                    composable(route = "encontreFavoritos") {
                        FavoritosScreen(
                            navController,
                            listPrestadoresViewModel,
                            sharedPreferencesHelper
                        )
                    }
                    composable(route = "PerfilPrestadorScreen") {
                        PerfilPrestadorScreen(navController, sharedPreferencesHelper)
                    }

                    composable(route = "PerfilContratanteScreen") {
                        PerfilContratanteScreen(navController, sharedPreferencesHelper)
                    }
                    composable(route = "Demand") {
                        Demand(navController, sharedPreferencesHelper)
                    }
                    composable(route = "NotificacoesScreen") {
                        NotificacoesScreen(navController, sharedPreferencesHelper)
                    }


                }
            }
        }
    }
}


