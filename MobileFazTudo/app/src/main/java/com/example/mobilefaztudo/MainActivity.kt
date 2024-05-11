package com.example.mobilefaztudo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilefaztudo.ui.theme.MobileFazTudoTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val repository = LoginRepository()
        loginViewModel = LoginViewModel(repository)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileFazTudoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    LoginScreen(loginViewModel)
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
     lateinit var loginViewModel: LoginViewModel
    MobileFazTudoTheme {
        LoginScreen(loginViewModel)
    }
}

