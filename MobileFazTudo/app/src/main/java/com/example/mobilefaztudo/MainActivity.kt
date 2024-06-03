package com.example.mobilefaztudo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilefaztudo.api.User
import com.example.mobilefaztudo.repository.ListDemandasRepository
import com.example.mobilefaztudo.repository.ListProviders
import com.example.mobilefaztudo.repository.LoginRepository
import com.example.mobilefaztudo.repository.RegisterContractorRepository
import com.example.mobilefaztudo.repository.RegisterProviderRepository
import com.example.mobilefaztudo.sharedPreferences.SharedPreferencesHelper
import com.example.mobilefaztudo.ui.theme.MobileFazTudoTheme
import com.example.mobilefaztudo.view.CadastroContratanteEtapa2
import com.example.mobilefaztudo.view.CadastroPrestadorEtapa2
import com.example.mobilefaztudo.view.CadastroScreenEtapa1
import com.example.mobilefaztudo.view.FavoritosScreen
import com.example.mobilefaztudo.view.LoginScreen
import com.example.mobilefaztudo.view.SplashScreen
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
        listPrestadoresViewModel = ListPrestadoresViewModel(repositoryListP, sharedPreferencesHelper)

        val repositoryListD = ListDemandasRepository()
        listDemandasViewModel = ListDemandasViewModel(repositoryListD, sharedPreferencesHelper)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileFazTudoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash"){
                    composable(route = "login"){ LoginScreen(loginViewModel,navController) }
                    composable(route = "cadastro1"){ CadastroScreenEtapa1(navController) }
                    composable(route = "cadastro2C"){ CadastroContratanteEtapa2(contratanteViewModel,navController) }
                    composable(route = "cadastro2P"){ CadastroPrestadorEtapa2(prestadorViewModel,navController) }
                    composable(route = "splash"){ SplashScreen(navController) }
                    composable(route = "encontrePrestadores"){ encontrePrestadores(navController, listPrestadoresViewModel)}
                    composable(route = "encontreDemandas"){ encontreDemandas(navController, listDemandasViewModel)}
                    composable(route = "encontreFavoritos"){ FavoritosScreen(navController, listPrestadoresViewModel)}

                }
            }
        }
    }
}


